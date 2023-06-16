package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.AbstractCopperField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperAccount extends AbstractCopperObject {
    String primaryTimezone;
    Settings settings;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Settings extends AbstractCopperField {
        Boolean settingTeamPermissionsEnabled;
        Boolean settingEnableLeads;
    }
}
