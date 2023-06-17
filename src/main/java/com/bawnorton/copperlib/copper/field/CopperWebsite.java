package com.bawnorton.copperlib.copper.field;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperWebsite extends AbstractCopperField {
    @SerializedName("url")
    public String url;

    @SerializedName("category")
    public String category;
}
