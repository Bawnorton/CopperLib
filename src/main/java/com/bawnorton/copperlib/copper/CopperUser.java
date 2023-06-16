package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.AbstractCopperField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperUser extends AbstractCopperObject {
    String email;
    List<Group> groups;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Group extends AbstractCopperField {
        Integer id;
        String name;
    }
}
