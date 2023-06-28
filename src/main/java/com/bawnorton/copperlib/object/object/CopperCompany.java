package com.bawnorton.copperlib.object.object;

import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.field.*;
import com.bawnorton.copperlib.object.search.CopperCompanySearchParameters;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCompany extends AbstractSearchableCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("address")
    CopperAddress address;

    @SerializedName("assignee_id")
    Integer assigneeId;

    @SerializedName("contact_type_id")
    Integer contactTypeId;

    @SerializedName("details")
    String details;

    @SerializedName("email_domain")
    String emailDomain;

    @SerializedName("phone_numbers")
    List<CopperPhoneNumber> phoneNumbers;

    @SerializedName("socials")
    List<CopperSocial> socials;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("websites")
    List<CopperWebsite> websites;

    @SerializedName("custom_fields")
    List<CopperCustomField> customFields;

    @SerializedName("interaction_count")
    Integer interactionCount;

    @SerializedName("date_created")
    Integer dateCreated;

    @SerializedName("date_modified")
    Integer dateModified;

    public static CopperCompanySearchParameters.Builder getSearchParametersBuilder() {
        return CopperCompanySearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.COMPANY;
    }
}
