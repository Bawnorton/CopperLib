package com.bawnorton.copperlib.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public enum CopperType {
    @SerializedName("lead") LEAD(CopperLead.class),
    @SerializedName("person") PERSON(CopperPerson.class),
    @SerializedName("company") COMPANY(CopperCompany.class),
    @SerializedName("opportunity") OPPORTUNITY(CopperOpportunity.class),
    @SerializedName("project") PROJECT(CopperProject.class),
    @SerializedName("task") TASK(CopperTask.class),
    @SerializedName("activity") ACTIVITY(CopperActivity.class),
    @SerializedName("user") USER(CopperUser.class),
    // non-object types
    @SerializedName("filedocument") FILE_DOCUMENT(null);

    private final Class<? extends SearchableCopperObject> clazz;
    private List<CopperType> relatedTypes = List.of();

    static {
        LEAD.relatedTypes = List.of(CopperType.TASK);
        PERSON.relatedTypes = List.of(CopperType.COMPANY, CopperType.OPPORTUNITY, CopperType.TASK, CopperType.PROJECT);
        COMPANY.relatedTypes = List.of(CopperType.PERSON, CopperType.OPPORTUNITY, CopperType.TASK, CopperType.PROJECT);
        OPPORTUNITY.relatedTypes = List.of(CopperType.PERSON, CopperType.COMPANY, CopperType.TASK, CopperType.PROJECT);
        PROJECT.relatedTypes = List.of(CopperType.PERSON, CopperType.COMPANY, CopperType.OPPORTUNITY, CopperType.TASK);
        TASK.relatedTypes = List.of(CopperType.PERSON, CopperType.COMPANY, CopperType.OPPORTUNITY, CopperType.LEAD, CopperType.PROJECT);
    }

    CopperType(Class<? extends SearchableCopperObject> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends SearchableCopperObject> getObjectClass() {
        return clazz;
    }

    public boolean isValidRelatedType(CopperType type) {
        for (CopperType relatedType : relatedTypes) {
            if (relatedType == type) return true;
        }
        return false;
    }

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
            case FILE_DOCUMENT -> "filedocuments";
        };
    }

    public static CopperType fromPluralString(String plural) {
        return switch (plural) {
            case "leads" -> LEAD;
            case "people" -> PERSON;
            case "companies" -> COMPANY;
            case "opportunities" -> OPPORTUNITY;
            case "projects" -> PROJECT;
            case "tasks" -> TASK;
            case "activities" -> ACTIVITY;
            case "users" -> USER;
            case "filedocuments" -> FILE_DOCUMENT;
            default -> throw new IllegalArgumentException("Plural " + plural + " is not a CopperType");
        };
    }

    public static CopperType fromClass(Class<? extends SearchableCopperObject> clazz) {
        for (CopperType type : values()) {
            if (type.clazz == clazz) return type;
        }
        throw new IllegalArgumentException("Class " + clazz.getName() + " is not a CopperType");
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public List<CopperType> getValidRelatedTypes() {
        return relatedTypes;
    }
}
