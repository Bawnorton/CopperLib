package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.AbstractCopperField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperUser extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("groups")
    List<Group> groups;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Group extends AbstractCopperField {
        @SerializedName("id")
        Integer id;

        @SerializedName("name")
        String name;
    }
}
