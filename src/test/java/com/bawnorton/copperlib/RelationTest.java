package com.bawnorton.copperlib;

import com.bawnorton.copperlib.object.CopperCompany;
import com.bawnorton.copperlib.object.CopperPerson;
import com.bawnorton.copperlib.object.relation.CopperRelation;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RelationTest extends TestingBase {
    @Test
    void testPersonRelations() {
        int id = 122551975;
        CopperPerson person = copperApi.getPerson(id);
        copperApi.populateRelations(person, relations -> {
            JsonArray rawRelations = getRawRelations(person.getCopperType().plural(), person.getId());
            List<CopperRelation> relationsList = new ArrayList<>();
            for(int i = 0; i < rawRelations.size(); i++) {
                relationsList.add(GSON.fromJson(rawRelations.get(i), CopperRelation.class));
            }
            assert relations.equals(relationsList) : "Relations not equal";
        });
    }

    @Test
    void testCompanyRelations() {
        int id = 62792296;
        CopperCompany company = copperApi.getCompany(id);
        copperApi.populateRelations(company, relations -> {
            JsonArray rawRelations = getRawRelations(company.getCopperType().plural(), company.getId());
            List<CopperRelation> relationsList = new ArrayList<>();
            for(int i = 0; i < rawRelations.size(); i++) {
                relationsList.add(GSON.fromJson(rawRelations.get(i), CopperRelation.class));
            }
            assert relations.equals(relationsList) : "Relations not equal";
        });
    }
}
