package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.AbstractCopperField;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperActivity extends AbstractCopperObject {
    CopperActivityType type;
    Integer parent;
    String details;
    Integer userId;
    Integer activityDate;
    JsonObject oldValue;
    JsonObject newValue;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CopperActivityType extends AbstractCopperField {
        Integer id;
        String category;
        String name;
        Boolean isDisabled;
        Boolean countsAsInteraction;
    }
}
