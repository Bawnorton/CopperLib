package com.bawnorton.copperlib.copper;

import com.google.gson.Gson;
import lombok.Data;

@Data
public abstract class AbstractCopperObject {
    Integer id;
    String name;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
