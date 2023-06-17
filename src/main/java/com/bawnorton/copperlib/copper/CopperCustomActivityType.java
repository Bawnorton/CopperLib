package com.bawnorton.copperlib.copper;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCustomActivityType extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("company_id")
    Integer companyId;

    @SerializedName("icon_type")
    String iconType;

    @SerializedName("is_disabled")
    Boolean isDisabled;

    @SerializedName("is_interaction")
    Boolean isInteraction;

    @SerializedName("is_default_task_type")
    Boolean isDefaultTaskType;
}