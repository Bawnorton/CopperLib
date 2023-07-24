package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.object.field.CopperCustomField;
import com.bawnorton.copperlib.object.search.CopperTaskSearchParameters;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperTask extends SearchableCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("related_resource")
    JsonObject relatedResource;

    @SerializedName("assignee_id")
    Integer assigneeId;

    @SerializedName("due_date")
    Integer dueDate;

    @SerializedName("reminder_date")
    Integer reminderDate;

    @SerializedName("completed_date")
    Integer completedDate;

    @SerializedName("priority")
    String priority;

    @SerializedName("status")
    String status;

    @SerializedName("details")
    String details;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("custom_fields")
    List<CopperCustomField> customFields;

    @SerializedName("date_created")
    Integer dateCreated;

    @SerializedName("date_modified")
    Integer dateModified;

    @SerializedName("custom_activity_type_id")
    Integer customActivityTypeId;

    public static CopperTaskSearchParameters.Builder getSearchParametersBuilder() {
        return CopperTaskSearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.TASK;
    }
}
