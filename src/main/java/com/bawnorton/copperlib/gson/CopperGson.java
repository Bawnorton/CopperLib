package com.bawnorton.copperlib.gson;

import com.bawnorton.copperlib.object.SearchableCopperObject;
import com.bawnorton.copperlib.object.field.CopperField;
import com.bawnorton.copperlib.object.relation.CopperRelationMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CopperGson {
    private static Gson GSON;

    public static Gson getInstance() {
        if (GSON == null) {
            GSON = new GsonBuilder()
                    .setExclusionStrategies(new CopperExclusionStrategy())
                    .registerTypeAdapterFactory(new StrictTypeAdapterFactory(SearchableCopperObject.class))
                    .registerTypeAdapterFactory(new StrictTypeAdapterFactory(CopperField.class))
                    .registerTypeAdapter(CopperRelationMap.class, new CopperRelationMapAdapter())
                    .setPrettyPrinting()
                    .create();
        }
        return GSON;
    }
}
