package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.object.field.CopperCustomField;
import com.bawnorton.copperlib.object.search.CopperOpportunitySearchParameters;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperOpportunity extends AbstractSearchableCopperObject {
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

    @SerializedName("monetary_unit")
    String monetaryUnit;

    @SerializedName("monetary_value")
    String monetaryValue;

    @SerializedName("converted_unit")
    String convertedUnit;

    @SerializedName("converted_value")
    String convertedValue;

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

    @SerializedName("leads_converted_from")
    List<JsonObject> leadsConvertedFrom;

    @SerializedName("custom_fields")
    List<CopperCustomField> customFields;

    @SerializedName("interaction_count")
    Integer interactionCount;

    @SerializedName("date_created")
    Integer dateCreated;

    @SerializedName("date_modified")
    Integer dateModified;

    @SerializedName("date_stage_changed")
    Integer dateStageChanged;

    @SerializedName("date_last_contacted")
    Integer dateLateContacted;

    @SerializedName("date_lead_created")
    Integer dateLeadCreated;

    public static CopperOpportunitySearchParameters.Builder getSearchParametersBuilder() {
        return CopperOpportunitySearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.OPPORTUNITY;
    }
}
