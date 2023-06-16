package com.bawnorton.copperlib.copper.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperWebsite extends AbstractCopperField {
    public String url;
    public String category;

}
