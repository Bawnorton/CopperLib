package com.bawnorton.copperlib.object.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

@Data
public abstract class AbstractCopperObject {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public abstract Integer getId();

    public String asJsonString() {
        return GSON.toJson(this);
    }

    public boolean equals(Object obj) {
        if (obj instanceof AbstractCopperObject abstractCopperObject) {
            return asJsonString().equals(abstractCopperObject.asJsonString());
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
