
package com.bawnorton.copperlib.api;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {
    private final String token;
    private final String email;

    public AuthenticationInterceptor(String token, String email) {
        this.token = token;
        this.email = email;
    }

    @Override
    public @NotNull Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader("X-PW-AccessToken", token)
                .addHeader("X-PW-Application", "developer_api")
                .addHeader("X-PW-UserEmail", email)
                .addHeader("Content-Type", "application/json")
                .build();
        return chain.proceed(newRequest);
    }

}
