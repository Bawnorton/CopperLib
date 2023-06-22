package com.bawnorton.copperlib.object.object;

import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperAccount extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("primary_timezone")
    String primaryTimezone;

    @SerializedName("settings")
    Settings settings;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Settings extends AbstractCopperField {
        @SerializedName("setting_team_permissions_enabled")
        Boolean settingTeamPermissionsEnabled;

        @SerializedName("setting_enable_leads")
        Boolean settingEnableLeads;
    }
}
