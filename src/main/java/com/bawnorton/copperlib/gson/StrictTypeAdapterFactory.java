package com.bawnorton.copperlib.gson;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;

public class StrictTypeAdapterFactory implements TypeAdapterFactory {
    private final Class<?> clazz;

    public StrictTypeAdapterFactory(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<?> rawType = type.getRawType();
        if (clazz.isAssignableFrom(rawType)) {
            return new StrictTypeAdapter<>(gson.getDelegateAdapter(this, type));
        }
        return null;
    }

    private static class StrictTypeAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> delegateAdapter;

        public StrictTypeAdapter(TypeAdapter<T> delegateAdapter) {
            this.delegateAdapter = delegateAdapter;
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            delegateAdapter.write(out, value);
        }

        @Override
        public T read(JsonReader in) {
            JsonElement jsonElement = Streams.parse(in);
            if(jsonElement.isJsonNull()) return null;

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            T targetObject = delegateAdapter.fromJsonTree(jsonObject);

            for (String key : jsonObject.keySet()) {
                try {
                    Field field = findMatchingField(targetObject.getClass(), key);
                    if (field == null) {
                        throw new JsonParseException("StrictTypeAdapter expected field \"" + key + "\" in " + targetObject.getClass().getSimpleName() + " for input JSON, but it was not found");
                    }
                } catch (NoSuchFieldException e) {
                    throw new JsonParseException("Error accessing fields: " + e.getMessage());
                }
            }

            return targetObject;
        }

        private Field findMatchingField(Class<?> clazz, String key) throws NoSuchFieldException {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                SerializedName annotation = field.getAnnotation(SerializedName.class);
                if (field.getName().equals(key) || (annotation != null && annotation.value().equals(key))) {
                    return field;
                }
            }

            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                return findMatchingField(superclass, key);
            }

            return null;
        }
    }
}
