package com.bawnorton.copperlib.object.object;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperLossReason extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;
}