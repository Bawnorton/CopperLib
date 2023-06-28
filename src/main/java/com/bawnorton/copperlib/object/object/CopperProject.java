package com.bawnorton.copperlib.object.object;

import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.bawnorton.copperlib.object.field.CopperCustomField;
import com.bawnorton.copperlib.object.field.CopperRelatedResource;
import com.bawnorton.copperlib.object.search.CopperProjectSearchParameters;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperProject extends AbstractSearchableCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("related_resource")
    CopperRelatedResource relatedResource;

    @SerializedName("assignee_id")
    Integer assigneeId;

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

    public static CopperProjectSearchParameters.Builder getSearchParametersBuilder() {
        return CopperProjectSearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.PROJECT;
    }
}
