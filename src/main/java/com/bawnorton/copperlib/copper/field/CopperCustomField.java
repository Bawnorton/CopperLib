package com.bawnorton.copperlib.copper.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCustomField extends AbstractCopperField {
    public String customFieldDefinitionId;
    public String value;
}
