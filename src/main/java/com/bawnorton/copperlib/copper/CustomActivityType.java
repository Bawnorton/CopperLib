package com.bawnorton.copperlib.copper;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomActivityType extends AbstractCopperObject {
    Integer companyId;
    String iconType;
    Boolean isDisabled;
    Boolean isInteraction;
    Boolean isDefaultTaskType;
}