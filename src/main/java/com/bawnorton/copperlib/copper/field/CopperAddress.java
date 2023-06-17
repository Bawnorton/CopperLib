package com.bawnorton.copperlib.copper.field;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperAddress extends AbstractCopperField {
    @SerializedName("street")
    String street;

    @SerializedName("city")
    String city;

    @SerializedName("state")
    String state;

    @SerializedName("postal_code")
    String postalCode;

    @SerializedName("country")
    String country;
}