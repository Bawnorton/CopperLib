
package com.bawnorton.copperlib.api;

import com.bawnorton.copperlib.object.*;
import com.bawnorton.copperlib.object.relation.CopperRelation;
import com.bawnorton.copperlib.object.search.*;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.*;

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

    @GET("{resource}/{id}/related")
    Single<List<CopperRelation>> getAllRelatedTo(@Path("resource") String resource, @Path("id") int id);

    @GET("{resource}/{id}/related/{type}")
    Single<List<CopperRelation>> getTypeRelatedTo(@Path("resource") String resource, @Path("id") int id, @Path("type") String type);

    @PUT("people/{id}")
    Single<Object> updatePerson(@Path("id") int id, @Body CopperPerson update);

    @PUT("companies/{id}")
    Single<Object> updateCompany(@Path("id") int id, @Body CopperCompany update);

    @PUT("leads/{id}")
    Single<Object> updateLead(@Path("id") int id, @Body CopperLead update);

    @PUT("opportunities/{id}")
    Single<Object> updateOpportunity(@Path("id") int id, @Body CopperOpportunity update);

    @PUT("projects/{id}")
    Single<Object> updateProject(@Path("id") int id, @Body CopperProject update);

    @PUT("tasks/{id}")
    Single<Object> updateTask(@Path("id") int id, @Body CopperTask update);

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