package com.bawnorton.copperlib.object;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperTag extends CopperObject {
    @SerializedName("name")
    String name;

    @SerializedName("count")
    Integer count;

    @SerializedName("count_people")
    Integer countPeople;

    @SerializedName("count_companies")
    Integer countCompanies;

    @SerializedName("count_opportunities")
    Integer countOpportunities;

    @SerializedName("count_projects")
    Integer countProjects;

    @SerializedName("count_tasks")
    Integer countTasks;

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Tags do not have IDs");
    }
}
