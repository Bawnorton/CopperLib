
package com.bawnorton.copperlib.api;

import com.bawnorton.copperlib.copper.object.*;
import com.bawnorton.copperlib.copper.search.*;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface CopperService {
    @GET("account")
    Single<CopperAccount> getAccount();

    @GET("users/{id}")
    Single<CopperUser> getUser(@Path("id") int id);

    @GET("leads/{id}")
    Single<CopperLead> getLead(@Path("id") int id);

    @GET("people/{id}")
    Single<CopperPerson> getPerson(@Path("id") int id);

    @GET("companies/{id}")
    Single<CopperCompany> getCompany(@Path("id") int id);

    @GET("opportunities/{id}")
    Single<CopperOpportunity> getOpportunity(@Path("id") int id);

    @GET("projects/{id}")
    Single<CopperProject> getProject(@Path("id") int id);

    @GET("taks/{id}")
    Single<CopperTask> getTask(@Path("id") int id);

    @GET("activities/{id}")
    Single<CopperActivity> getActivity(@Path("id") int id);

    @POST("users/search")
    Single<List<CopperUser>> listUsers(@Body CopperUserSearchParameters searchParameters);

    @POST("leads/search")
    Single<List<CopperLead>> listLeads(@Body CopperLeadSearchParameters searchParameters);

    @POST("people/search")
    Single<List<CopperPerson>> listPeople(@Body CopperPersonSearchParameters searchParameters);

    @POST("companies/search")
    Single<List<CopperCompany>> listCompanies(@Body CopperCompanySearchParameters searchParameters);

    @POST("opportunities/search")
    Single<List<CopperOpportunity>> listOpportunities(@Body CopperOpportunitySearchParameters searchParameters);

    @POST("projects/search")
    Single<List<CopperProject>> listProjects(@Body CopperProjectSearchParameters searchParameters);

    @POST("tasks/search")
    Single<List<CopperTask>> listTasks(@Body CopperTaskSearchParameters searchParameters);

    @POST("activities/search")
    Single<List<CopperActivity>> listActivities(@Body CopperActivitySearchParameters searchParameters);
}