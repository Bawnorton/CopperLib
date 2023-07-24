package com.bawnorton.copperlib.object.relation;

import com.bawnorton.copperlib.gson.annotation.Exclude;
import com.bawnorton.copperlib.object.SearchableCopperObject;
import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.field.CopperField;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperRelation extends CopperField {
    @SerializedName("id")
    Integer id;

    @SerializedName("type")
    CopperType copperType;

    @Exclude
    SearchableCopperObject actual;

    public Optional<SearchableCopperObject> getActual() {
        return Optional.ofNullable(actual);
    }
}
