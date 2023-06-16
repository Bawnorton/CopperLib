package com.bawnorton.copperlib.copper;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperTag extends AbstractCopperObject {
    Integer count;
    Integer countPeople;
    Integer countCompanies;
    Integer countOpportunities;
    Integer countProjects;
    Integer countTasks;
}
