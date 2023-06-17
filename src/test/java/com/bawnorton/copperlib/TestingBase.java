package com.bawnorton.copperlib;

import com.bawnorton.copperlib.api.CopperApi;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class TestingBase {
    protected static final CopperApi copperApi;
    protected static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger("CopperLib");
        LOGGER.info("Starting Tests");
        try {
            JsonReader reader = new JsonReader(new FileReader("src/test/resources/.auth.json"));
            AuthJson authJson = new Gson().fromJson(reader, AuthJson.class);
            copperApi = new CopperApi(authJson.getToken(), authJson.getEmail());
            LOGGER.info("CopperApi initialized with email: " + authJson.getEmail());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(".auth.json not found");
        }
    }

    @Data
    private static class AuthJson {
        String token;
        String email;
    }
}
