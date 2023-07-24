package com.bawnorton.copperlib.util;

import com.bawnorton.copperlib.object.SearchableCopperObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class CopperSet<T extends SearchableCopperObject> extends HashSet<T> {
    public CopperSet() {
        super();
    }

    public static <T extends SearchableCopperObject> CopperSet<T> of(Collection<T> entries) {
        CopperSet<T> copperSet = new CopperSet<>();
        copperSet.addAll(entries);
        return copperSet;
    }

    public CopperSet<T> subset(Selection<T> selection) {
        CopperSet<T> subset = new CopperSet<>();
        for(T object: this) {
            if(selection.select(object)) {
                subset.add(object);
            }
        }
        return subset;
    }

    public Optional<T> getFromId(int id) {
        for(T object: this) {
            if(object.getId() == id) {
                return Optional.of(object);
            }
        }
        return Optional.empty();
    }

    public Class<?> getType() {
        if(isEmpty()) {
            return null;
        }
        return iterator().next().getClass();
    }
}
