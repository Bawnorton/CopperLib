package com.bawnorton.copperlib.gson;

import com.bawnorton.copperlib.object.relation.CopperRelation;
import com.bawnorton.copperlib.object.relation.CopperRelationMap;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CopperRelationMapAdapter implements JsonSerializer<CopperRelationMap>, JsonDeserializer<CopperRelationMap> {

    @Override
    public CopperRelationMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CopperRelationMap map = new CopperRelationMap();
        JsonObject jsonObject = json.getAsJsonObject();
        for(JsonElement element: jsonObject.get("relations").getAsJsonArray()) {
            JsonObject relationObject = element.getAsJsonObject();
            String relationKey = relationObject.keySet().iterator().next();
            JsonArray relationArray = relationObject.get(relationKey).getAsJsonArray();
            for(JsonElement relationElement: relationArray) {
                CopperRelation relation = context.deserialize(relationElement, CopperRelation.class);
                map.addRelation(relationKey, relation);
            }
        }
        long lastUpdated = jsonObject.get("last_updated").getAsLong();
        map.update(lastUpdated);
        return map;
    }

    @Override
    public JsonElement serialize(CopperRelationMap src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        JsonArray relations = new JsonArray();
        for(String key: src.getAllRelations().keySet()) {
            JsonObject relationObject = new JsonObject();
            relationObject.add(key, context.serialize(src.getAllRelations().get(key)));
            relations.add(relationObject);
        }
        jsonObject.add("relations", relations);
        jsonObject.addProperty("last_updated", src.getLastUpdated());
        return jsonObject;
    }
}
