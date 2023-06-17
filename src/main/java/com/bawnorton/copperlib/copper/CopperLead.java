package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.*;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperLead extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("address")
    CopperAddress address;

    @SerializedName("assignee_id")
    Integer assigneeId;

    @SerializedName("company_name")
    String companyName;

    @SerializedName("customer_source_id")
    Integer customerSourceId;

    @SerializedName("details")
    String details;

    @SerializedName("email")
    CopperEmail email;

    @SerializedName("monetary_value")
    Integer monetaryValue;

    @SerializedName("phone_numbers")
    List<CopperPhoneNumber> phoneNumbers;

    @SerializedName("socials")
    List<CopperSocial> socials;

    @SerializedName("status")
    String status;

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
}
