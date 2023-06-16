
package com.bawnorton.copperlib.api;

import com.bawnorton.copperlib.copper.CopperPerson;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class CopperApi {
    private static final String BASE_URL = "https://api.copper.com/developer_api/v1/";
    private final CopperService service;

    public CopperApi(final String token, final String email) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(token, email))
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(CopperService.class);
    }

    public CopperPerson getPerson(int id) {
        return service.getPerson(id).blockingGet();
    }

}