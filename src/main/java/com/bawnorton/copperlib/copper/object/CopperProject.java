package com.bawnorton.copperlib.copper.object;

import com.bawnorton.copperlib.copper.CopperType;
import com.bawnorton.copperlib.copper.field.CopperCustomField;
import com.bawnorton.copperlib.copper.search.CopperProjectSearchParameters;
import com.bawnorton.copperlib.copper.search.SearchParameters;
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
    Integer relatedResource;

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

    @Override
    public CopperProjectSearchParameters.Builder getSearchParametersBuilder() {
        return CopperProjectSearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.PROJECT;
    }
}
