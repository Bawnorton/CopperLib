package com.bawnorton.copperlib.object.relation;

import com.bawnorton.copperlib.gson.annotation.Exclude;
import com.bawnorton.copperlib.object.AbstractSearchableCopperObject;
import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperRelation extends AbstractCopperField {
    @SerializedName("id")
    Integer id;

    @SerializedName("type")
    CopperType type;

    @Exclude
    AbstractSearchableCopperObject actual;
}
