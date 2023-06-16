package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.CopperCustomField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperTask extends AbstractCopperObject {
    Integer relatedResource;
    Integer assigneeId;
    Integer dueDate;
    Integer reminderDate;
    Integer completedDate;
    String priority;
    String status;
    String details;
    List<String> tags;
    List<CopperCustomField> customFields;
    Integer dateCreated;
    Integer dateModified;
}
