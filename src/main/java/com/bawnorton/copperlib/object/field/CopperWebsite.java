package com.bawnorton.copperlib.object.field;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperWebsite extends CopperField {
    @SerializedName("url")
    String url;

    @SerializedName("category")
    String category;
}
