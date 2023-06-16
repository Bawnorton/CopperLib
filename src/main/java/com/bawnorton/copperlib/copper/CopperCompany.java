package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCompany extends AbstractCopperObject {
    CopperAddress address;
    Integer assigneeId;
    Integer contactTypeId;
    String details;
    String emailDomain;
    List<CopperPhoneNumber> phoneNumbers;
    List<CopperSocial> socials;
    List<String> tags;
    List<CopperWebsite> websites;
    List<CopperCustomField> customFields;
    Integer interactionCount;
    Integer dateCreated;
    Integer dateModified;
}
