package com.bawnorton.copperlib.copper.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperPhoneNumber extends AbstractCopperField {
    String number;
    String category;
}
