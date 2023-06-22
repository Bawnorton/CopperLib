package com.bawnorton.copperlib.object.field;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperPhoneNumber extends AbstractCopperField {
    @SerializedName("number")
    String number;

    @SerializedName("category")
    String category;
}
