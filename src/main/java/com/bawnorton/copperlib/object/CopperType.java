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

    public String plural() {
        return switch (this) {
            case LEAD -> "leads";
            case PERSON -> "people";
            case COMPANY -> "companies";
            case OPPORTUNITY -> "opportunities";
            case PROJECT -> "projects";
            case TASK -> "tasks";
            case ACTIVITY -> "activities";
            case USER -> "users";
        };
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
