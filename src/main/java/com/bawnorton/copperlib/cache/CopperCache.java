package com.bawnorton.copperlib.cache;

import com.bawnorton.copperlib.gson.CopperGson;
import com.bawnorton.copperlib.object.*;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CopperCache<T extends AbstractSearchableCopperObject> {
    private static final Gson GSON = CopperGson.getInstance();

    @SerializedName("last_updated")
    private long lastUpdated = 0;

    @SerializedName("entries")
    private final List<T> entries = new ArrayList<>();

    public CopperCache() {}

    public static <T extends AbstractSearchableCopperObject> CopperCache<T> load(String path, Class<T> clazz) {
        try {
            return load(path).as(clazz);
        } catch (ClassCastException e) {
            throw new RuntimeException("Cache for " + clazz.getSimpleName() + " is not of type " + clazz.getSimpleName());
        }
    }

    private static <T extends AbstractSearchableCopperObject> CopperCache<T> load(String path) {
        try(JsonReader reader = new JsonReader(new FileReader(path))) {
            CopperCache<T> cache = GSON.fromJson(reader, CopperCache.class);
            if (cache == null) return new CopperCache<>();
            return cache;
        } catch (IOException e) {
            return new CopperCache<>();
        }
    }

    public <E extends AbstractSearchableCopperObject> CopperCache<E> as(Class<E> clazz) throws ClassCastException {
        CopperCache<E> newCache = new CopperCache<>();
        for(T entry: entries) {
            newCache.add(clazz.cast(entry));
        }
        newCache.update(lastUpdated);
        return newCache;
    }

    public void save(String path) {
        try(FileWriter writer = new FileWriter(path)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(long time) {
        lastUpdated = time;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void addAll(Iterable<T> objects) {
        for (T obj : objects) {
            add(obj);
        }
    }

    public boolean add(T object) {
        return entries.add(object);
    }

    public Set<T> remove(Iterable<T> objects) {
        HashSet<T> removed = new HashSet<>();
        for (T obj : objects) {
            if (entries.remove(obj)) removed.add(obj);
        }
        return removed;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void clear() {
        entries.clear();
        lastUpdated = 0;
    }

    public static CopperCacheMap loadAll(String parentDirectory) {
        return CopperCacheMap.of(
                CopperCacheMap.entry(CopperPerson.class, load(parentDirectory + "/" + CopperType.PERSON.plural() + ".json")),
                CopperCacheMap.entry(CopperCompany.class, load(parentDirectory + "/" + CopperType.COMPANY.plural() + ".json")),
                CopperCacheMap.entry(CopperOpportunity.class, load(parentDirectory + "/" + CopperType.OPPORTUNITY.plural() + ".json")),
                CopperCacheMap.entry(CopperLead.class, load(parentDirectory + "/" + CopperType.LEAD.plural() + ".json")),
                CopperCacheMap.entry(CopperActivity.class, load(parentDirectory + "/" + CopperType.ACTIVITY.plural() + ".json")),
                CopperCacheMap.entry(CopperProject.class, load(parentDirectory + "/" + CopperType.PROJECT.plural() + ".json")),
                CopperCacheMap.entry(CopperTask.class, load(parentDirectory + "/" + CopperType.TASK.plural() + ".json")),
                CopperCacheMap.entry(CopperUser.class, load(parentDirectory + "/" + CopperType.USER.plural() + ".json"))
        );
    }

    @Override
    public String toString() {
        return "CopperCache{" +
                "lastUpdated=" + lastUpdated +
                ", entries=" + entries.size() +
                '}';
    }

    public CopperType getType() {
        return CopperType.fromClass(entries.get(0).getClass());
    }
}
