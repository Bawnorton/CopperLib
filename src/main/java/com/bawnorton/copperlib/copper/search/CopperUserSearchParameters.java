package com.bawnorton.copperlib.copper.search;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public class CopperUserSearchParameters implements SearchParameters {
    @lombok.Builder.Default
    @SerializedName("page_number")
    Integer pageNumber = 1;

    @lombok.Builder.Default
    @SerializedName("page_size")
    Integer pageSize = 20;

    public static class Builder implements SearchParameters.Builder {
    }
}
