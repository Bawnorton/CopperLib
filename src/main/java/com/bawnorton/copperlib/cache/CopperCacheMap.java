package com.bawnorton.copperlib.cache;

import com.bawnorton.copperlib.object.AbstractSearchableCopperObject;
import com.bawnorton.copperlib.object.CopperType;

import java.util.HashMap;
import java.util.Map;

public class CopperCacheMap extends HashMap<Class<? extends AbstractSearchableCopperObject>, CopperCache<? extends AbstractSearchableCopperObject>> {

    @SafeVarargs
    public static CopperCacheMap of(Map.Entry<Class<? extends AbstractSearchableCopperObject>, CopperCache<? extends AbstractSearchableCopperObject>>... entries) {
        CopperCacheMap map = new CopperCacheMap();
        for (Map.Entry<Class<? extends AbstractSearchableCopperObject>, CopperCache<? extends AbstractSearchableCopperObject>> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static Map.Entry<Class<? extends AbstractSearchableCopperObject>, CopperCache<? extends AbstractSearchableCopperObject>> entry(Class<? extends AbstractSearchableCopperObject> clazz, CopperCache<? extends AbstractSearchableCopperObject> cache) {
        return Map.entry(clazz, cache);
    }

    public <T extends AbstractSearchableCopperObject> CopperCache<T> get(Class<T> clazz) {
        try {
            CopperCache<T> cache = super.get(clazz).as(clazz);
             put(clazz, cache);
             return cache;
        } catch (ClassCastException e) {
            throw new RuntimeException("Cache for " + clazz.getSimpleName() + " is not of type " + clazz.getSimpleName(), e);
        }
    }

    public void updateAll(long l) {
        for (CopperCache<? extends AbstractSearchableCopperObject> cache : values()) {
            cache.update(l);
        }
    }

    public void saveAll(String parentDirectory) {
        for (Map.Entry<Class<? extends AbstractSearchableCopperObject>, CopperCache<? extends AbstractSearchableCopperObject>> cache : entrySet()) {
            cache.getValue().save(parentDirectory + "/" + CopperType.fromClass(cache.getKey()).plural() + ".json");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Class<? extends AbstractSearchableCopperObject>, CopperCache<? extends AbstractSearchableCopperObject>> cache : entrySet()) {
            builder.append(cache.getKey().getSimpleName()).append(": ").append(cache.getValue()).append("\n");
        }
        return builder.toString();
    }
}
