package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.AbstractCopperField;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperActivity extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("type")
    CopperActivityType type;

    @SerializedName("parent")
    Integer parent;

    @SerializedName("details")
    String details;

    @SerializedName("user_id")
    Integer userId;

    @SerializedName("activity_date")
    Integer activityDate;

    @SerializedName("old_value")
    JsonObject oldValue;

    @SerializedName("new_value")
    JsonObject newValue;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CopperActivityType extends AbstractCopperField {
        @SerializedName("id")
        Integer id;

        @SerializedName("category")
        String category;

        @SerializedName("name")
        String name;

        @SerializedName("is_disabled")
        Boolean isDisabled;

        @SerializedName("count_as_interaction")
        Boolean countsAsInteraction;
    }
}
