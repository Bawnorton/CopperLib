package com.bawnorton.copperlib.copper.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperAddress extends AbstractCopperField {
    String street;
    String city;
    String state;
    String postalCode;
    String country;
}