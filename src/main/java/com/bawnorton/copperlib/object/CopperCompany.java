package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.object.field.*;
import com.bawnorton.copperlib.object.search.CopperCompanySearchParameters;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCompany extends SearchableCopperObject {
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

    public @Nullable Date getDateCreated() {
        if(dateCreated == null) return null;
        return new Date(dateCreated * 1000L);
    }

    public @Nullable Date getDateModified() {
        if(dateModified == null) return null;
        return new Date(dateModified * 1000L);
    }

    public static CopperCompanySearchParameters.Builder getSearchParametersBuilder() {
        return CopperCompanySearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.COMPANY;
    }
}
