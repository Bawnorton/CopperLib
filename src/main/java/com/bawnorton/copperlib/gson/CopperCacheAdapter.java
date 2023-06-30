package com.bawnorton.copperlib.gson;

import com.bawnorton.copperlib.cache.CopperCache;
import com.bawnorton.copperlib.object.AbstractSearchableCopperObject;
import com.bawnorton.copperlib.object.CopperType;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CopperCacheAdapter implements JsonSerializer<CopperCache<? extends AbstractSearchableCopperObject>>, JsonDeserializer<CopperCache<? extends AbstractSearchableCopperObject>> {

    @Override
    public CopperCache<? extends AbstractSearchableCopperObject> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CopperCache<? extends AbstractSearchableCopperObject> cache = new CopperCache<>();
        JsonObject jsonObject = json.getAsJsonObject();
        CopperType type = CopperType.fromPluralString(jsonObject.get("type").getAsString());
        for(JsonElement element: jsonObject.get("entries").getAsJsonArray()) {
            cache.add(context.deserialize(element, type.getObjectClass()));
        }
        cache.update(jsonObject.get("last_updated").getAsLong());
        return cache;
    }

    @Override
    public JsonElement serialize(CopperCache<? extends AbstractSearchableCopperObject> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getType().plural());
        jsonObject.addProperty("last_updated", src.getLastUpdated());
        JsonArray entries = new JsonArray();
        for(AbstractSearchableCopperObject entry: src.getEntries()) {
            entries.add(context.serialize(entry));
        }
        jsonObject.add("entries", entries);
        return jsonObject;
    }
}
