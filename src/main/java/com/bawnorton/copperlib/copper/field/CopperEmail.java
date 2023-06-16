package com.bawnorton.copperlib.copper.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperEmail extends AbstractCopperField {
    String email;
    String category;
}
