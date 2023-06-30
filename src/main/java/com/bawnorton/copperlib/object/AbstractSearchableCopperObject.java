package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.object.relation.CopperRelation;

import java.util.*;

public abstract class AbstractSearchableCopperObject extends AbstractCopperObject {
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

    public Optional<List<CopperRelation>> getRelations(CopperType ofType) {
        if (relations == null) return Optional.empty();
        List<CopperRelation> filtered = new ArrayList<>();
        for (CopperRelation relation : relations) {
            if (relation.getType().equals(ofType)) filtered.add(relation);
        }
        return Optional.of(filtered);
    }
}
