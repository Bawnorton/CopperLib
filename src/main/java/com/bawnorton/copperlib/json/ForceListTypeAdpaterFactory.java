package com.bawnorton.copperlib.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ForceListTypeAdpaterFactory<E> implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        TypeAdapter<T> delegateAdapter = gson.getAdapter(type);
        TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegateAdapter.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonToken token = in.peek();
                if (token == JsonToken.BEGIN_ARRAY || token == JsonToken.NULL) {
                    return delegateAdapter.read(in);
                } else {
                    JsonElement element = elementAdapter.read(in);
                    JsonArray array = new JsonArray();
                    array.add(element);
                    return delegateAdapter.fromJsonTree(array);
                }
            }
        };
    }
}
