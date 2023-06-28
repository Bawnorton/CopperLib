package com.bawnorton.copperlib.object.object;

import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.bawnorton.copperlib.object.search.CopperActivitySearchParameters;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperActivity extends AbstractSearchableCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("type")
    Type type;

    @SerializedName("parent")
    JsonElement parent;

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
    JsonElement oldValue;

    @SerializedName("new_value")
    JsonElement newValue;

    public static CopperActivitySearchParameters.Builder getSearchParametersBuilder() {
        return CopperActivitySearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.ACTIVITY;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Type extends AbstractCopperField {
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
