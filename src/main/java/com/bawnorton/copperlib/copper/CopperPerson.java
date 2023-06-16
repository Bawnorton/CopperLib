package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperPerson extends AbstractCopperObject {
    String prefix;
    String firstName;
    String middleName;
    String lastName;
    String suffix;
    CopperAddress address;
    Integer assigneeId;
    Integer companyId;
    String companyName;
    Integer contactTypeId;
    String details;
    List<CopperEmail> emails;
    List<CopperPhoneNumber> phoneNumbers;
    List<CopperSocial> socials;
    List<String> tags;
    String title;
    List<CopperWebsite> websites;
    List<CopperCustomField> customFields;
    Integer interactionCount;
    Integer dateCreated;
    Integer dateModified;
}