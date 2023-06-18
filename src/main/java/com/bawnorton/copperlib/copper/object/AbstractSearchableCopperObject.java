package com.bawnorton.copperlib.copper.object;

import com.bawnorton.copperlib.copper.CopperType;
import com.bawnorton.copperlib.copper.search.SearchParameters;

public abstract class AbstractSearchableCopperObject extends AbstractCopperObject {
    public abstract SearchParameters.Builder getSearchParametersBuilder();
    public abstract CopperType getCopperType();
}
