package com.bawnorton.copperlib;

import com.bawnorton.copperlib.object.CopperPerson;
import com.bawnorton.copperlib.object.CopperType;
import org.junit.jupiter.api.Test;

public class RelationTest extends TestingBase {
    @Test
    void testPersonRelation() {
        int id = 149200992;
        CopperPerson person = copperApi.getPerson(id);
        copperApi.populateRelations(person, CopperType.COMPANY);
        LOGGER.info("Person Relations: {}", person.getRelations(CopperType.COMPANY));
    }
}
