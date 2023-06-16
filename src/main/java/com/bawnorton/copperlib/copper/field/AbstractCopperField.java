package com.bawnorton.copperlib.copper.field;

import com.google.gson.Gson;

public abstract class AbstractCopperField {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
