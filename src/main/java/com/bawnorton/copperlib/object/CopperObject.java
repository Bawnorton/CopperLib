package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.gson.CopperGson;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public abstract class CopperObject {
    private static final Gson GSON = CopperGson.getInstance();

    public abstract Integer getId();

    public String asJsonString() {
        return GSON.toJson(this);
    }

    public JsonObject asJson() {
        return GSON.fromJson(asJsonString(), JsonObject.class);
    }

    public <T extends CopperObject> @NotNull T with(String key, Object value, Class<T> clazz) {
        JsonElement valueElement = GSON.toJsonTree(value);
        JsonObject json = asJson();
        json.add(key, valueElement);
        T instance = GSON.fromJson(json, clazz);
        if(instance == null) throw new IllegalStateException("Failed to create instance of " + clazz.getSimpleName() + " from json " + json);
        return instance;
    }

    public CopperObject with(String key, Object value) {
        return with(key, value, this.getClass());
    }

    public Object get(String key) {
        return asJson().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return GSON.fromJson(GSON.toJson(get(key)), clazz);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CopperObject copperObject) {
            return asJsonString().equals(copperObject.asJsonString());
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
