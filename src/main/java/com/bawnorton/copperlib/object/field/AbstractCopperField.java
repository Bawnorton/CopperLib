package com.bawnorton.copperlib.object.field;

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
    public boolean equals(Object obj) {
        if (obj instanceof AbstractCopperField abstractCopperField) {
            return asJsonString().equals(abstractCopperField.asJsonString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return asJsonString().hashCode();
    }

    @Override
    public String toString() {
        return asJsonString();
    }
}
