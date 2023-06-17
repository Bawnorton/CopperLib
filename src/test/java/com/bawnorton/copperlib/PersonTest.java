package com.bawnorton.copperlib;


import com.bawnorton.copperlib.copper.CopperPerson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest extends TestingBase {
    @Test
    void testPerson() {
        LOGGER.info("Testing Person");
        CopperPerson person = copperApi.getPerson(149200992); // Test Contact ID
        assertEquals(person.getDateCreated(), 1681235539);
    }
}