package com.bawnorton.copperlib.copper.object;

import com.google.gson.Gson;
import lombok.Data;

@Data
public abstract class AbstractCopperObject {
    public abstract Integer getId();

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
