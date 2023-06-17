package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.CopperCustomField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperTask extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("related_resource")
    Integer relatedResource;

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
}
