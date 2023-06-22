package com.bawnorton.copperlib.object.object;

import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.field.*;
import com.bawnorton.copperlib.object.search.CopperLeadSearchParameters;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperLead extends AbstractSearchableCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("first_name")
    String firstName;

    @SerializedName("middle_name")
    String middleName;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("prefix")
    String prefix;

    @SerializedName("suffix")
    String suffix;

    @SerializedName("address")
    CopperAddress address;

    @SerializedName("assignee_id")
    Integer assigneeId;

    @SerializedName("company_name")
    String companyName;

    @SerializedName("customer_source_id")
    Integer customerSourceId;

    @SerializedName("converted_opportunity_id")
    Integer convertedOpportunityId;

    @SerializedName("converted_contact_id")
    Integer convertedContactId;

    @SerializedName("converted_at")
    Integer convertedAt;

    @SerializedName("details")
    String details;

    @SerializedName("email")
    CopperEmail email;

    @SerializedName("monetary_unit")
    String monetaryUnit;

    @SerializedName("monetary_value")
    String monetaryValue;

    @SerializedName("converted_unit")
    String convertedUnit;

    @SerializedName("converted_value")
    String convertedValue;

    @SerializedName("phone_numbers")
    List<CopperPhoneNumber> phoneNumbers;

    @SerializedName("socials")
    List<CopperSocial> socials;

    @SerializedName("status")
    String status;

    @SerializedName("status_id")
    Integer statusId;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("title")
    String title;

    @SerializedName("websites")
    List<CopperWebsite> websites;

    @SerializedName("custom_fields")
    List<CopperCustomField> customFields;

    @SerializedName("date_created")
    Integer dateCreated;

    @SerializedName("date_modified")
    Integer dateModified;

    @SerializedName("date_last_contacted")
    Integer dateLastContacted;

    @SerializedName("interaction_count")
    Integer interactionCount;

    @Override
    public CopperLeadSearchParameters.Builder getSearchParametersBuilder() {
        return CopperLeadSearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.LEAD;
    }
}
