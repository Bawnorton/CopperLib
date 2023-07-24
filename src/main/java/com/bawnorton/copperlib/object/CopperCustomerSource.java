package com.bawnorton.copperlib.object;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCustomerSource extends CopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;
}
