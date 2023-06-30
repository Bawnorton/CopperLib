package com.bawnorton.copperlib.gson;

import com.bawnorton.copperlib.gson.annotation.Exclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class CopperExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes field) {
        return field.getAnnotation(Exclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
