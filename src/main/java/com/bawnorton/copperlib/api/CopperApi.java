package com.bawnorton.copperlib.api;

import com.bawnorton.copperlib.api.intercept.AuthenticationInterceptor;
import com.bawnorton.copperlib.api.intercept.RateLimitIntercpetor;
import com.bawnorton.copperlib.exception.CopperException;
import com.bawnorton.copperlib.gson.CopperGson;
import com.bawnorton.copperlib.object.*;
import com.bawnorton.copperlib.object.relation.CopperRelation;
import com.bawnorton.copperlib.object.search.*;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CopperApi {
    private static final String BASE_URL = "https://api.copper.com/developer_api/v1/";
    public static final Logger LOGGER = LogManager.getLogger("CopperApi");
    private final CopperService service;

    public CopperApi(@Nullable final String token, @Nullable final String email) throws IllegalArgumentException {
        if (token == null) throw new IllegalArgumentException("Token cannot be null");
        if (email == null) throw new IllegalArgumentException("Email cannot be null");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(token, email))
                .addNetworkInterceptor(new RateLimitIntercpetor())
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CopperGson.getInstance()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(CopperService.class);

        RxJavaPlugins.setErrorHandler(getErrorHandler());
    }

    public static Consumer<? super Throwable> getErrorHandler() {
        return e -> {
            if (e instanceof UndeliverableException) e = e.getCause();
            if (e instanceof IOException) return;
            if (e instanceof InterruptedException) return;
            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                return;
            }
            if (e instanceof IllegalStateException) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                return;
            }
            if(e instanceof CopperException) {
                LOGGER.error(e);
                return;
            }
            if(e instanceof HttpException httpException) {
                try {
                    LOGGER.error("HttpException: " + httpException.code() + " " + httpException.message() + " " + httpException.response().errorBody().string());
                } catch (RuntimeException ex) {
                    LOGGER.error("HttpException: " + httpException.code() + " " + httpException.message());
                }
                return;
            }
            LOGGER.error("Undeliverable exception received, not sure what to do", e);
        };
    }

    public CopperAccount getAccount() {
        return service.getAccount().blockingGet();
    }

    public CopperUser getUser(int id) {
        return service.getUser(id).blockingGet();
    }

    public CopperLead getLead(int id) {
        return service.getLead(id).blockingGet();
    }

    public CopperPerson getPerson(int id) {
        return service.getPerson(id).blockingGet();
    }

    public CopperCompany getCompany(int id) {
        return service.getCompany(id).blockingGet();
    }

    public CopperOpportunity getOpportunity(int id) {
        return service.getOpportunity(id).blockingGet();
    }

    public CopperProject getProject(int id) {
        return service.getProject(id).blockingGet();
    }

    public CopperTask getTask(int id) {
        return service.getTask(id).blockingGet();
    }

    public CopperActivity getActivity(int id) {
        return service.getActivity(id).blockingGet();
    }

    public void populateRelations(SearchableCopperObject object) {
        populateRelations(object, relations -> {
            for (CopperRelation relation : relations) {
                object.addRelation(relation);
            }
        });
    }

    public void populateRelations(SearchableCopperObject object, Consumer<List<CopperRelation>> onSuccess) {
        populateRelations(object.getCopperType(), object.getId(), onSuccess);
    }

    public void populateRelations(CopperType type, int id, Consumer<List<CopperRelation>> onSuccess) {
        service.getAllRelatedTo(type.plural(), id).blockingSubscribe(onSuccess, getErrorHandler());
    }

    public void populateRelationsOfType(SearchableCopperObject object, CopperType relatedType) {
        populateRelationsOfType(object, relatedType, relations -> {
            for (CopperRelation relation : relations) {
                object.addRelation(relation);
            }
        });
    }

    public void populateRelationsOfType(SearchableCopperObject object, CopperType relatedType, Consumer<List<CopperRelation>> onSuccess) {
        populateRelationsOfType(object.getCopperType(), object.getId(), relatedType, onSuccess);
    }

    public void populateRelationsOfType(CopperType type, int id, CopperType relatedType, Consumer<List<CopperRelation>> onSuccess) {
        if(!type.isValidRelatedType(relatedType)) throw new IllegalArgumentException("Invalid related copperType \"" + relatedType + "\" for copperType \"" + type + "\". Valid types are: " + type.getValidRelatedTypes());
        service.getTypeRelatedTo(type.plural(), id, relatedType.plural()).blockingSubscribe(onSuccess, getErrorHandler());
    }

    public boolean addTag(CopperPerson person, String tag) {
        if(person.getTags().contains(tag.toLowerCase())) return false;
        CopperPerson newPerson = new CopperPerson();
        newPerson.setTags(person.getTags());
        newPerson.getTags().add(tag.toLowerCase());
        service.updatePerson(person.getId(), newPerson).blockingSubscribe();
        return true;
    }

    public boolean removeTag(CopperPerson person, String tag) {
        if(!person.getTags().contains(tag.toLowerCase())) return false;
        CopperPerson newPerson = new CopperPerson();
        newPerson.setTags(person.getTags());
        newPerson.getTags().remove(tag.toLowerCase());
        service.updatePerson(person.getId(), person).blockingSubscribe();
        return true;
    }

    public boolean addTag(CopperCompany company, String tag) {
        if(company.getTags().contains(tag.toLowerCase())) return false;
        CopperCompany newCompany = new CopperCompany();
        newCompany.setTags(company.getTags());
        newCompany.getTags().add(tag.toLowerCase());
        service.updateCompany(company.getId(), newCompany).blockingSubscribe();
        return true;
    }

    public boolean removeTag(CopperCompany company, String tag) {
        if(!company.getTags().contains(tag.toLowerCase())) return false;
        CopperCompany newCompany = new CopperCompany();
        newCompany.setTags(company.getTags());
        newCompany.getTags().remove(tag.toLowerCase());
        service.updateCompany(company.getId(), company).blockingSubscribe();
        return true;
    }

    public boolean addTag(CopperLead lead, String tag) {
        if(lead.getTags().contains(tag.toLowerCase())) return false;
        CopperLead newLead = new CopperLead();
        newLead.setTags(lead.getTags());
        newLead.getTags().add(tag.toLowerCase());
        service.updateLead(lead.getId(), newLead).blockingSubscribe();
        return true;
    }

    public boolean removeTag(CopperLead lead, String tag) {
        if(!lead.getTags().contains(tag.toLowerCase())) return false;
        CopperLead newLead = new CopperLead();
        newLead.setTags(lead.getTags());
        newLead.getTags().remove(tag.toLowerCase());
        service.updateLead(lead.getId(), lead).blockingSubscribe();
        return true;
    }

    public boolean addTag(CopperOpportunity opportunity, String tag) {
        if(opportunity.getTags().contains(tag.toLowerCase())) return false;
        CopperOpportunity newOpportunity = new CopperOpportunity();
        newOpportunity.setTags(opportunity.getTags());
        newOpportunity.getTags().add(tag.toLowerCase());
        service.updateOpportunity(opportunity.getId(), newOpportunity).blockingSubscribe();
        return true;
    }

    public boolean removeTag(CopperOpportunity opportunity, String tag) {
        if(!opportunity.getTags().contains(tag.toLowerCase())) return false;
        CopperOpportunity newOpportunity = new CopperOpportunity();
        newOpportunity.setTags(opportunity.getTags());
        newOpportunity.getTags().remove(tag.toLowerCase());
        service.updateOpportunity(opportunity.getId(), newOpportunity).blockingSubscribe();
        return true;
    }

    public boolean addTag(CopperProject project, String tag) {
        if(project.getTags().contains(tag.toLowerCase())) return false;
        CopperProject newProject = new CopperProject();
        newProject.setTags(project.getTags());
        newProject.getTags().add(tag.toLowerCase());
        service.updateProject(project.getId(), newProject).blockingSubscribe();
        return true;
    }

    public boolean removeTag(CopperProject project, String tag) {
        if(!project.getTags().contains(tag.toLowerCase())) return false;
        CopperProject newProject = new CopperProject();
        newProject.setTags(project.getTags());
        newProject.getTags().remove(tag.toLowerCase());
        service.updateProject(project.getId(), newProject).blockingSubscribe();
        return true;
    }

    public boolean addTag(CopperTask task, String tag) {
        if(task.getTags().contains(tag.toLowerCase())) return false;
        CopperTask newTask = new CopperTask();
        newTask.setTags(task.getTags());
        newTask.getTags().add(tag.toLowerCase());
        service.updateTask(task.getId(), newTask).blockingSubscribe();
        return true;
    }

    public boolean removeTag(CopperTask task, String tag) {
        if(!task.getTags().contains(tag.toLowerCase())) return false;
        CopperTask newTask = new CopperTask();
        newTask.setTags(task.getTags());
        newTask.getTags().remove(tag.toLowerCase());
        service.updateTask(task.getId(), newTask).blockingSubscribe();
        return true;
    }

    public boolean addTag(SearchableCopperObject copperObject, String tag) {
        return switch (copperObject.getCopperType()) {
            case PERSON -> addTag((CopperPerson) copperObject, tag);
            case COMPANY -> addTag((CopperCompany) copperObject, tag);
            case LEAD -> addTag((CopperLead) copperObject, tag);
            case OPPORTUNITY -> addTag((CopperOpportunity) copperObject, tag);
            case PROJECT -> addTag((CopperProject) copperObject, tag);
            case TASK -> addTag((CopperTask) copperObject, tag);
            default -> throw new IllegalArgumentException(copperObject.getCopperType().name() + " does not support tags");
        };
    }

    public boolean removeTag(SearchableCopperObject copperObject, String tag) {
        return switch (copperObject.getCopperType()) {
            case PERSON -> removeTag((CopperPerson) copperObject, tag);
            case COMPANY -> removeTag((CopperCompany) copperObject, tag);
            case LEAD -> removeTag((CopperLead) copperObject, tag);
            case OPPORTUNITY -> removeTag((CopperOpportunity) copperObject, tag);
            case PROJECT -> removeTag((CopperProject) copperObject, tag);
            case TASK -> removeTag((CopperTask) copperObject, tag);
            default -> throw new IllegalArgumentException(copperObject.getCopperType().name() + " does not support tags");
        };
    }

    public List<CopperPerson> getPeople(CopperPersonSearchParameters parameters) {
        try {
            List<CopperPerson> people = service.listPeople(parameters).blockingGet();
            LOGGER.info("Got " + people.size() + " people");
            return people;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperCompany> getCompanies(CopperCompanySearchParameters parameters) {
        try {
            List<CopperCompany> companies = service.listCompanies(parameters).blockingGet();
            LOGGER.info("Got " + companies.size() + " companies");
            return companies;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperOpportunity> getOpportunities(CopperOpportunitySearchParameters parameters) {
        try {
            List<CopperOpportunity> opportunities = service.listOpportunities(parameters).blockingGet();
            LOGGER.info("Got " + opportunities.size() + " opportunities");
            return opportunities;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperLead> getLeads(CopperLeadSearchParameters parameters) {
        try {
            List<CopperLead> leads = service.listLeads(parameters).blockingGet();
            LOGGER.info("Got " + leads.size() + " leads");
            return leads;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperProject> getProjects(CopperProjectSearchParameters parameters) {
        try {
            List<CopperProject> projects = service.listProjects(parameters).blockingGet();
            LOGGER.info("Got " + projects.size() + " projects");
            return projects;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperTask> getTasks(CopperTaskSearchParameters parameters) {
        try {
            List<CopperTask> tasks = service.listTasks(parameters).blockingGet();
            LOGGER.info("Got " + tasks.size() + " tasks");
            return tasks;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperActivity> getActivities(CopperActivitySearchParameters parameters) {
        try {
            List<CopperActivity> activities = service.listActivities(parameters).blockingGet();
            LOGGER.info("Got " + activities.size() + " activities");
            return activities;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperUser> getUsers(CopperUserSearchParameters parameters) {
        try {
            List<CopperUser> users = service.listUsers(parameters).blockingGet();
            LOGGER.info("Got " + users.size() + " users");
            return users;
        } catch (HttpException e) {
            throw new CopperException(e);
        }
    }

    public List<CopperPerson> getAllPeople(CopperPersonSearchParameters parameters) {
        LOGGER.info("Getting all people with page size " + parameters.getPageSize());
        List<CopperPerson> people = new ArrayList<>();
        int expectedSize = parameters.getPageSize();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listPeople(parameters).blockingSubscribe(copperPeople -> {
                people.addAll(copperPeople);
                pageSize.set(copperPeople.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + people.size() + " people, expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return people;
    }

    public List<CopperCompany> getAllCompanies(CopperCompanySearchParameters parameters) {
        LOGGER.info("Getting all companies with page size " + parameters.getPageSize());
        List<CopperCompany> companies = new ArrayList<>();
        int expectedSize = parameters.getPageSize();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listCompanies(parameters).blockingSubscribe(copperCompanies -> {
                companies.addAll(copperCompanies);
                pageSize.set(copperCompanies.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + companies.size() + " companies, expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return companies;
    }

    public List<CopperOpportunity> getAllOpportunities(CopperOpportunitySearchParameters parameters) {
        LOGGER.info("Getting all opportunities with page size " + parameters.getPageSize());
        List<CopperOpportunity> opportunities = new ArrayList<>();
        int expectedSize = parameters.getPageSize();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listOpportunities(parameters).blockingSubscribe(copperOpportunities -> {
                opportunities.addAll(copperOpportunities);
                pageSize.set(copperOpportunities.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + opportunities.size() + " opportunities, expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return opportunities;
    }

    public List<CopperLead> getAllLeads(CopperLeadSearchParameters parameters) {
        LOGGER.info("Getting all leads with page size " + parameters.getPageSize());
        List<CopperLead> leads = new ArrayList<>();
        int expectedSize = parameters.getPageSize();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listLeads(parameters).blockingSubscribe(copperLeads -> {
                leads.addAll(copperLeads);
                pageSize.set(copperLeads.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + leads.size() + " leads, expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return leads;
    }

    public List<CopperProject> getAllProjects(CopperProjectSearchParameters parameters) {
        LOGGER.info("Getting all projects with page size " + parameters.getPageSize());
        List<CopperProject> projects = new ArrayList<>();
        int expectedSize = parameters.getPageSize();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listProjects(parameters).blockingSubscribe(copperProjects -> {
                projects.addAll(copperProjects);
                pageSize.set(copperProjects.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + projects.size() + " projects, expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return projects;
    }

    public List<CopperTask> getAllTasks(CopperTaskSearchParameters parameters) {
        LOGGER.info("Getting all tasks with page size " + parameters.getPageSize());
        List<CopperTask> tasks = new ArrayList<>();
        int expectedSize = parameters.getPageSize();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listTasks(parameters).blockingSubscribe(copperTasks -> {
                tasks.addAll(copperTasks);
                pageSize.set(copperTasks.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + tasks.size() + " tasks, expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return tasks;
    }

    public List<CopperActivity> getAllActivities(CopperActivitySearchParameters parameters) {
        LOGGER.info("Getting all activities with page size " + parameters.getPageSize());
        List<CopperActivity> activities = new ArrayList<>();
        int expectedSize = parameters.getPageSize();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listActivities(parameters).blockingSubscribe(copperActivities -> {
                activities.addAll(copperActivities);
                pageSize.set(copperActivities.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + activities.size() + " activities, expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return activities;
    }

    public List<CopperUser> getAllUsers(CopperUserSearchParameters parameters) {
        LOGGER.info("Getting all users with page size " + parameters.getPageSize());
        List<CopperUser> users = new ArrayList<>();
        AtomicInteger page = new AtomicInteger(parameters.getPageNumber());
        int expectedSize = parameters.getPageSize();
        AtomicInteger pageSize = new AtomicInteger(expectedSize);
        while (pageSize.get() == expectedSize) {
            LOGGER.info("Getting page " + page.get());
            parameters.setPageNumber(page.get());
            service.listUsers(parameters).blockingSubscribe(copperUser -> {
                users.addAll(copperUser);
                pageSize.set(copperUser.size());
                page.getAndIncrement();
            }, throwable -> {
                throw new CopperException(throwable);
            });
        }
        LOGGER.info("Got " + users.size() + " users expected page size was " + expectedSize + " but got " + pageSize.get() + ", stopping");
        return users;
    }
}
