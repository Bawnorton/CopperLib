package com.bawnorton.copperlib.exception;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;

public class CopperException extends RuntimeException {
    private String message;

    public CopperException(HttpException httpException) {
        try {
            Response<?> response = httpException.response();
            if (response == null) return;

            try(ResponseBody body = response.errorBody()) {
                if (body == null) return;

                message = "HTTP " + httpException.code() + ": " + body.string();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CopperException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

