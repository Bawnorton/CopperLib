package com.bawnorton.copperlib.object.field;

import com.bawnorton.copperlib.object.CopperType;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperRelatedResource extends AbstractCopperField {
    @SerializedName("id")
    Integer id;

    @SerializedName("type")
    CopperType type;
}
