package com.bawnorton.copperlib.copper.field;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperEmail extends AbstractCopperField {
    @SerializedName("email")
    String email;

    @SerializedName("category")
    String category;
}
