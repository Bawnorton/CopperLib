package com.bawnorton.copperlib;

import com.bawnorton.copperlib.object.*;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

public class CopperObjectTest extends TestingBase {

    @Test
    void testAccountObj() {
        CopperAccount account = copperApi.getAccount();
        JsonObject rawAccount = getRawCopperObject("account");
        CopperAccount parsedAccount = GSON.fromJson(rawAccount, CopperAccount.class);

        if(!account.equals(parsedAccount)) {
            testEqualFields(account, parsedAccount);
            throw new AssertionError("Account did not match");
        }
    }

    @Test
    void testUserObj() {
        int id = 999850; // Test User ID
        CopperUser user = copperApi.getUser(id);
        JsonObject rawUser = getRawCopperObject("users", id);
        CopperUser parsedUser = GSON.fromJson(rawUser, CopperUser.class);

        if(!user.equals(parsedUser)) {
            testEqualFields(user, parsedUser);
            throw new AssertionError("User did not match");
        }
    }

    @Test
    void testLeadObj() {
        int id = 79901776; // Test Lead ID
        CopperLead lead = copperApi.getLead(id);
        JsonObject rawLead = getRawCopperObject("leads", id);
        CopperLead parsedLead = GSON.fromJson(rawLead, CopperLead.class);

        if(!lead.equals(parsedLead)) {
            testEqualFields(lead, parsedLead);
            throw new AssertionError("Lead did not match");
        }
    }

    @Test
    void testPersonObj() {
        int id = 149200992; // Test Contact ID
        CopperPerson person = copperApi.getPerson(id);
        JsonObject rawPerson = getRawCopperObject("people", id);
        CopperPerson parsedPerson = GSON.fromJson(rawPerson, CopperPerson.class);

        if(!person.equals(parsedPerson)) {
            testEqualFields(person, parsedPerson);
            throw new AssertionError("Person did not match");
        }
    }

    @Test
    void testCompanyObj() {
        int id = 62792296; // Test Company ID
        CopperCompany company = copperApi.getCompany(id);
        JsonObject rawCompany = getRawCopperObject("companies", id);
        CopperCompany parsedCompany = GSON.fromJson(rawCompany, CopperCompany.class);

        if(!company.equals(parsedCompany)) {
            testEqualFields(company, parsedCompany);
            throw new AssertionError("Company did not match");
        }
    }

    @Test
    void testOpportinuty() {
        int id = 29048315; // Test Opportunity ID
        CopperOpportunity opportunity = copperApi.getOpportunity(id);
        JsonObject rawOpportunity = getRawCopperObject("opportunities", id);
        CopperOpportunity parsedOpportunity = GSON.fromJson(rawOpportunity, CopperOpportunity.class);

        if(!opportunity.equals(parsedOpportunity)) {
            testEqualFields(opportunity, parsedOpportunity);
            throw new AssertionError("Opportunity did not match");
        }
    }

    @Test
    void testProjectObj() {
        int id = 1128834; // Test Project ID
        CopperProject project = copperApi.getProject(id);
        JsonObject rawProject = getRawCopperObject("projects", id);
        CopperProject parsedProject = GSON.fromJson(rawProject, CopperProject.class);

        if(!project.equals(parsedProject)) {
            testEqualFields(project, parsedProject);
            throw new AssertionError("Project did not match");
        }
    }

    @Test
    void testTaskObj() {
        LOGGER.info("Testing CopperTask Object");
        CopperTask task = copperApi.getTasks(CopperTask.getSearchParametersBuilder().build()).get(0);
        int id = task.getId();
        JsonObject rawTask = getRawCopperObject("tasks", id);
        CopperTask parsedTask = GSON.fromJson(rawTask, CopperTask.class);

        if(!task.equals(parsedTask)) {
            testEqualFields(task, parsedTask);
            throw new AssertionError("Task did not match");
        }
    }

    @Test
    void testActivityObj() {
        LOGGER.info("Testing CopperActivity Object");
        copperApi.getActivities(CopperActivity.getSearchParametersBuilder().fullResult(true).build());
//        int id = activity.getId();
//        JsonObject rawActivity = getRawCopperObject("activities", id);
//        CopperActivity parsedActivity = GSON.fromJson(rawActivity, CopperActivity.class);

//        if(!activity.equals(parsedActivity)) {
//            testEqualFields(activity, parsedActivity);
//            throw new AssertionError("Activity did not match");
//        }
    }
}
