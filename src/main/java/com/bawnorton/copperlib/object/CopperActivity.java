package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.object.field.CopperField;
import com.bawnorton.copperlib.object.search.CopperActivitySearchParameters;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperActivity extends SearchableCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("type")
    Type type;

    @SerializedName("parent")
    Parent parent;

    @SerializedName("details")
    String details;

    @SerializedName("user_id")
    Integer userId;

    @SerializedName("activity_date")
    Integer activityDate;

    @SerializedName("date_created")
    Integer dateCreated;

    @SerializedName("date_modified")
    Integer dateModified;

    @SerializedName("old_value")
    Value oldValue;

    @SerializedName("new_value")
    Value newValue;

    public static CopperActivitySearchParameters.Builder getSearchParametersBuilder() {
        return CopperActivitySearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.ACTIVITY;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Type extends CopperField {
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

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Value extends CopperField {
        @SerializedName("id")
        Integer id;

        @SerializedName("name")
        String name;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Parent extends CopperField {
        @SerializedName("id")
        Integer id;

        @SerializedName("type")
        String type;
    }
}
