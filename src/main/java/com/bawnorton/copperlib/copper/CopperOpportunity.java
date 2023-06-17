package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.CopperCustomField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperOpportunity extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("assignee_id")
    Integer assigneeId;

    @SerializedName("close_date")
    String closeDate;

    @SerializedName("company_id")
    Integer companyId;

    @SerializedName("company_name")
    String companyName;

    @SerializedName("customer_source_id")
    Integer customerSourceId;

    @SerializedName("details")
    String details;

    @SerializedName("loss_reason_id")
    Integer lossReasonId;

    @SerializedName("monetary_value")
    Integer monetaryValue;

    @SerializedName("pipeline_id")
    Integer pipelineId;

    @SerializedName("primary_contact_id")
    Integer primaryContactId;

    @SerializedName("priority")
    String priority;

    @SerializedName("pipeline_stage_id")
    Integer pipelineStageId;

    @SerializedName("status")
    String status;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("win_probability")
    Integer winProbability;

    @SerializedName("custom_fields")
    List<CopperCustomField> customFields;

    @SerializedName("interaction_count")
    Integer interactionCount;

    @SerializedName("date_created")
    Integer dateCreated;

    @SerializedName("date_modified")
    Integer dateModified;
}
