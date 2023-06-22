package com.bawnorton.copperlib.object;

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

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
