package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.object.relation.CopperRelation;

import java.util.*;

import static com.bawnorton.copperlib.api.CopperApi.LOGGER;

public abstract class SearchableCopperObject extends CopperObject {
    private Set<CopperRelation> relations;

    public abstract CopperType getCopperType();

    public void addRelations(List<CopperRelation> relations) {
        if (this.relations == null) this.relations = new HashSet<>();
        this.relations.addAll(relations);
    }

    public void addRelation(CopperRelation relation) {
        if (this.relations == null) this.relations = new HashSet<>();
        this.relations.add(relation);
    }

    public void clearRelations() {
        this.relations = null;
    }

    public <T extends SearchableCopperObject> T as(Class<T> clazz) {
        return clazz.cast(this);
    }

    public Optional<List<CopperRelation>> getRelations(CopperType ofType) {
        if (relations == null) return Optional.empty();
        List<CopperRelation> filtered = new ArrayList<>();
        for (CopperRelation relation : relations) {
            if(relation.getCopperType() == null) {
                LOGGER.warn("Relation copperType is null for relation " + relation + " on object " + this);
                continue;
            }
            if(relation.getCopperType().equals(ofType)) filtered.add(relation);
        }
        return Optional.of(filtered);
    }

    public Optional<List<CopperRelation>> getRelations() {
        if (relations == null) return Optional.empty();
        return Optional.of(new ArrayList<>(relations));
    }
}
