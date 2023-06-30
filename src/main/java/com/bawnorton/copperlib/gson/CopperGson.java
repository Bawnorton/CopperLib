package com.bawnorton.copperlib.gson;

import com.bawnorton.copperlib.cache.CopperCache;
import com.bawnorton.copperlib.cache.CopperCacheMap;
import com.bawnorton.copperlib.object.AbstractSearchableCopperObject;
import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.bawnorton.copperlib.util.tuple.Pair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CopperGson {
    private static Gson GSON;

    public static Gson getInstance() {
        if (GSON == null) {
            GSON = new GsonBuilder()
                    .setExclusionStrategies(new CopperExclusionStrategy())
                    .registerTypeAdapterFactory(new StrictTypeAdapterFactory(AbstractSearchableCopperObject.class))
                    .registerTypeAdapterFactory(new StrictTypeAdapterFactory(AbstractCopperField.class))
                    .registerTypeAdapter(CopperCache.class, new CopperCacheAdapter())
                    .setPrettyPrinting()
                    .create();
        }
        return GSON;
    }
}
