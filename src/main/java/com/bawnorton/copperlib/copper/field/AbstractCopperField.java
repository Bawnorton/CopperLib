package com.bawnorton.copperlib.copper.field;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractCopperField {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public String asJsonString() {
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return asJsonString();
    }
}
