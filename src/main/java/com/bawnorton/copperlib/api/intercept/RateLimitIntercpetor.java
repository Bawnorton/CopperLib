package com.bawnorton.copperlib.api.intercept;

import com.google.common.util.concurrent.RateLimiter;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@SuppressWarnings("UnstableApiUsage")
public class RateLimitIntercpetor implements Interceptor {
    private final RateLimiter rateLimiter = RateLimiter.create(2);

    @Override
    public @NotNull Response intercept(Chain chain) throws IOException {
        rateLimiter.acquire(1);
        return chain.proceed(chain.request());
    }
}
