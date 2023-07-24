package com.bawnorton.copperlib.object.field;

import com.bawnorton.copperlib.gson.CopperGson;
import com.bawnorton.copperlib.object.CopperObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public abstract class CopperField {
    private static final Gson GSON = CopperGson.getInstance();

    public String asJsonString() {
        return GSON.toJson(this);
    }

    public JsonObject asJson() {
        return GSON.fromJson(asJsonString(), JsonObject.class);
    }

    public static <T extends CopperField> T fromJson(JsonElement json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public <T extends CopperField> @NotNull T with(String key, Object value, Class<T> clazz) {
        JsonElement valueElement = GSON.toJsonTree(value);
        JsonObject json = asJson();
        json.add(key, valueElement);
        T instance = GSON.fromJson(json, clazz);
        if(instance == null) throw new IllegalStateException("Failed to create instance of " + clazz.getSimpleName() + " from json " + json);
        return instance;
    }

    public CopperField with(String key, Object value) {
        return with(key, value, this.getClass());
    }

    public Object get(String key) {
        return asJson().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return GSON.fromJson(GSON.toJson(get(key)), clazz);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CopperField copperField) {
            return asJsonString().equals(copperField.asJsonString());
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
