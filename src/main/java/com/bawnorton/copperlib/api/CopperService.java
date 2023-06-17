
package com.bawnorton.copperlib.api;

import com.bawnorton.copperlib.copper.*;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CopperService {

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

}