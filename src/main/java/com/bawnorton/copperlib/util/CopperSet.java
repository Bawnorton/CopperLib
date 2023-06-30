package com.bawnorton.copperlib.util;

import com.bawnorton.copperlib.object.AbstractSearchableCopperObject;

import java.util.Collection;
import java.util.HashSet;

public class CopperSet<T extends AbstractSearchableCopperObject> extends HashSet<T> {
    public CopperSet() {
        super();
    }

    public static <T extends AbstractSearchableCopperObject> CopperSet<T> of(Collection<T> entries) {
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

    public Class<?> getType() {
        if(isEmpty()) {
            return null;
        }
        return iterator().next().getClass();
    }
}
