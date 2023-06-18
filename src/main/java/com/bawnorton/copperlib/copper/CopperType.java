package com.bawnorton.copperlib.copper;

import com.google.gson.annotations.SerializedName;

public enum CopperType {
    @SerializedName("lead") LEAD,
    @SerializedName("person") PERSON,
    @SerializedName("company") COMPANY,
    @SerializedName("opportunity") OPPORTUNITY,
    @SerializedName("project") PROJECT,
    @SerializedName("task") TASK,
    @SerializedName("activity") ACTIVITY,
    @SerializedName("user") USER;

    public static CopperType fromString(String type) {
        for (CopperType copperType : CopperType.values()) {
            if (copperType.toString().equalsIgnoreCase(type)) {
                return copperType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
