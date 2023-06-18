package com.bawnorton.copperlib.copper.object;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCustomerSource extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;
}
