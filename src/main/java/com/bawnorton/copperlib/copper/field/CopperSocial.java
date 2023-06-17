package com.bawnorton.copperlib.copper.field;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperSocial extends AbstractCopperField {
    @SerializedName("url")
    String url;

    @SerializedName("category")
    String category;
}
