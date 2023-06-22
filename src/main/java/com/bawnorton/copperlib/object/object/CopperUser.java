package com.bawnorton.copperlib.object.object;

import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.bawnorton.copperlib.object.search.CopperUserSearchParameters;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperUser extends AbstractSearchableCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("groups")
    List<Group> groups;

    @Override
    public CopperUserSearchParameters.Builder getSearchParametersBuilder() {
        return CopperUserSearchParameters.builder();
    }

    @Override
    public CopperType getCopperType() {
        return CopperType.USER;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Group extends AbstractCopperField {
        @SerializedName("id")
        Integer id;

        @SerializedName("name")
        String name;
    }
}