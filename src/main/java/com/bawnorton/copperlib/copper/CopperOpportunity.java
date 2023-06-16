package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.CopperCustomField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperOpportunity extends AbstractCopperObject {
    Integer assigneeId;
    String closeDate;
    Integer companyId;
    String companyName;
    Integer customerSourceId;
    String details;
    Integer lossReasonId;
    Integer monetaryValue;
    Integer pipelineId;
    Integer primaryContactId;
    String priority;
    Integer pipelineStageId;
    String status;
    List<String> tags;
    Integer winProbability;
    List<CopperCustomField> customFields;
    Integer interactionCount;
    Integer dateCreated;
    Integer dateModified;
}
