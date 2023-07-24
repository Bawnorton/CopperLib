package com.bawnorton.copperlib.object.relation;

import com.bawnorton.copperlib.object.SearchableCopperObject;
import com.bawnorton.copperlib.object.CopperType;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopperRelationMap {
    @SerializedName("relations")
    private final Map<String, List<CopperRelation>> relations;

    @SerializedName("last_updated")
    private long lastUpdated = 0;

    public CopperRelationMap() {
        relations = new HashMap<>();
    }

    public CopperRelationMap(Map<String, List<CopperRelation>> relations) {
        this.relations = relations;
    }

    public void update(long time) {
        lastUpdated = time;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public Map<String, List<CopperRelation>> getAllRelations() {
        return relations;
    }

    public boolean hasRelations(String relationKey) {
        return relations.containsKey(relationKey);
    }

    public boolean hasRelations(SearchableCopperObject object) {
        return hasRelations(object.getCopperType(), object.getId());
    }

    public boolean hasRelations(CopperType type, int id) {
        return hasRelations(type.plural() + "_" + id);
    }

    public boolean hasRelations(String type, int id) {
        return hasRelations(CopperType.fromPluralString(type), id);
    }

    public List<CopperRelation> getRelations(String relationKey) {
        return relations.get(relationKey);
    }

    public List<CopperRelation> getRelations(SearchableCopperObject object) {
        return getRelations(object.getCopperType(), object.getId());
    }

    public List<CopperRelation> getRelations(CopperType type, int id) {
        return getRelations(type.plural() + "_" + id);
    }

    public List<CopperRelation> getRelations(String type, int id) {
        return getRelations(CopperType.fromPluralString(type), id);
    }

    public void addRelations(String relationKey, List<CopperRelation> relations) {
        this.relations.put(relationKey, relations);
    }

    public void addRelations(SearchableCopperObject object, List<CopperRelation> relations) {
        addRelations(object.getCopperType(), object.getId(), relations);
    }

    public void addRelations(CopperType type, int id, List<CopperRelation> relations) {
        addRelations(type.plural() + "_" + id, relations);
    }

    public void addRelations(String type, int id, List<CopperRelation> relations) {
        addRelations(CopperType.fromPluralString(type), id, relations);
    }

    public void addRelation(String relationKey, CopperRelation relation) {
        if(!relations.containsKey(relationKey)) relations.put(relationKey, new ArrayList<>(List.of(relation)));
        else relations.get(relationKey).add(relation);
    }

    public void addRelation(SearchableCopperObject object, CopperRelation relation) {
        addRelation(object.getCopperType(), object.getId(), relation);
    }

    public void addRelation(CopperType type, int id, CopperRelation relation) {
        addRelation(type.plural() + "_" + id, relation);
    }

    public void addRelation(String type, int id, CopperRelation relation) {
        addRelation(CopperType.fromPluralString(type), id, relation);
    }

    public void clearRelations(String relationKey) {
        relations.remove(relationKey);
    }

    public void clearRelations(SearchableCopperObject object) {
        clearRelations(object.getCopperType(), object.getId());
    }

    public void clearRelations(CopperType type, int id) {
        clearRelations(type.plural() + "_" + id);
    }

    public void clearRelations(String type, int id) {
        clearRelations(CopperType.fromPluralString(type), id);
    }

    public void clearAllRelations() {
        relations.clear();
    }
}
