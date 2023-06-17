package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.*;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperPerson extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("prefix")
    String prefix;

    @SerializedName("first_name")
    String firstName;

    @SerializedName("middle_name")
    String middleName;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("suffix")
    String suffix;

    @SerializedName("address")
    CopperAddress address;

    @SerializedName("assignee_id")
    Integer assigneeId;

    @SerializedName("company_id")
    Integer companyId;

    @SerializedName("company_name")
    String companyName;

    @SerializedName("contact_type_id")
    Integer contactTypeId;

    @SerializedName("details")
    String details;

    @SerializedName("emails")
    List<CopperEmail> emails;

    @SerializedName("phone_numbers")
    List<CopperPhoneNumber> phoneNumbers;

    @SerializedName("socials")
    List<CopperSocial> socials;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("title")
    String title;

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
}