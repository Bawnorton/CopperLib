package com.bawnorton.copperlib.api;

import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.bawnorton.copperlib.object.object.*;
import com.bawnorton.copperlib.gson.StrictTypeAdapterFactory;
import com.google.gson.GsonBuilder;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class CopperApi {
    private static final String BASE_URL = "https://api.copper.com/developer_api/v1/";
    private final CopperService service;

    public CopperApi(@Nullable final String token, @Nullable final String email) throws IllegalArgumentException {
        if (token == null) throw new IllegalArgumentException("Token cannot be null");
        if (email == null) throw new IllegalArgumentException("Email cannot be null");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(token, email))
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .registerTypeAdapterFactory(new StrictTypeAdapterFactory(AbstractCopperObject.class))
                                .registerTypeAdapterFactory(new StrictTypeAdapterFactory(AbstractCopperField.class))
                                .setPrettyPrinting()
                                .create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(CopperService.class);
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
}
