package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperLead extends AbstractCopperObject {
    CopperAddress address;
    Integer assigneeId;
    String companyName;
    Integer customerSourceId;
    String details;
    CopperEmail email;
    Integer monetaryValue;
    List<CopperPhoneNumber> phoneNumbers;
    List<CopperSocial> socials;
    String status;
    List<String> tagss;
    String title;
    List<CopperWebsite> websites;
    List<CopperCustomField> customFields;
    Integer dateCreated;
    Integer dateModified;
}
