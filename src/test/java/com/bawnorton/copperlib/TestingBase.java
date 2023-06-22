package com.bawnorton.copperlib;

import com.bawnorton.copperlib.api.AuthenticationInterceptor;
import com.bawnorton.copperlib.api.CopperApi;
import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.bawnorton.copperlib.object.object.AbstractCopperObject;
import com.bawnorton.copperlib.gson.StrictTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import lombok.Data;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class TestingBase {
    protected static final CopperApi copperApi;
    protected static final OkHttpClient client;
    protected static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(new StrictTypeAdapterFactory(AbstractCopperObject.class))
            .registerTypeAdapterFactory(new StrictTypeAdapterFactory(AbstractCopperField.class))
            .setPrettyPrinting()
            .create();
    protected static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger("CopperLib");
        LOGGER.info("Starting Tests");
        try {
            JsonReader reader = new JsonReader(new FileReader("src/test/resources/.auth.json"));
            AuthJson authJson = new Gson().fromJson(reader, AuthJson.class);
            copperApi = new CopperApi(authJson.getToken(), authJson.getEmail());
            LOGGER.info("CopperApi initialized with email: " + authJson.getEmail());

            client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthenticationInterceptor(authJson.getToken(), authJson.getEmail()))
                    .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(".auth.json not found");
        }

    }

    protected JsonObject getRawCopperObject(String endpoint, int id) {
        Request request = new Request.Builder()
                .url("https://api.copper.com/developer_api/v1/" + endpoint + "/" + id)
                .build();
        return getRawCopperObject(request);
    }

    protected JsonObject getRawCopperObject(String endpoint) {
        Request request = new Request.Builder()
                .url("https://api.copper.com/developer_api/v1/" + endpoint)
                .build();
        return getRawCopperObject(request);
    }

    private JsonObject getRawCopperObject(Request request) {
        Call call = client.newCall(request);
        try(Response response = call.execute()) {
            ResponseBody body = response.body();
            if (body == null) throw new RuntimeException("Response body was null");

            return GSON.fromJson(body.string(), JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // compare all fields of two AbstractCopperObjects and log any differences
    protected <T extends AbstractCopperObject> void testEqualFields(T first, T second) {
        if(first == null || second == null) {
            LOGGER.error("One or more objects were null");
            return;
        }

        if(first.getClass() != second.getClass()) {
            LOGGER.error("Class mismatch");
            return;
        }

        List<Field> firstFields = List.of(first.getClass().getDeclaredFields());
        List<Field> secondFields = List.of(second.getClass().getDeclaredFields());
        if(firstFields.size() != secondFields.size()) {
            LOGGER.error("Field count mismatch");
            return;
        }

        for(int i = 0; i < firstFields.size(); i++) {
            Field firstField = firstFields.get(i);
            Field secondField = secondFields.get(i);
            assert firstField.getName().equals(secondField.getName());

            firstField.setAccessible(true);
            secondField.setAccessible(true);

            try {
                Object firstValue = firstField.get(first);
                Object secondValue = secondField.get(second);

                if(firstValue == null && secondValue == null) continue;
                if(firstValue == null || secondValue == null) {
                    LOGGER.error("Field " + firstField.getName() + " was null on one object but not the other");
                    LOGGER.error("first: " + firstValue);
                    LOGGER.error("second: " + secondValue);
                    continue;
                }

                if(!firstValue.equals(secondValue)) {
                    LOGGER.error("Field " + firstField.getName() + " did not match");
                    LOGGER.error("first: " + firstValue);
                    LOGGER.error("second: " + secondValue);
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Error accessing field " + firstField.getName());
            }
        }
    }

    @Data
    private static class AuthJson {
        String token;
        String email;
    }
}
