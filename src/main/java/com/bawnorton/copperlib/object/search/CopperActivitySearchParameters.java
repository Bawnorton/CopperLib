package com.bawnorton.copperlib.object.search;

import com.bawnorton.copperlib.object.CopperType;
import com.bawnorton.copperlib.object.SearchableCopperObject;
import com.bawnorton.copperlib.object.CopperActivity;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(builderClassName = "Builder")
public class CopperActivitySearchParameters implements SearchParameters {
    @SerializedName("parent")
    ActivityParent parent;

    @SerializedName("activity_types")
    List<CopperActivity.Type> activityTypes;

    @lombok.Builder.Default
    @SerializedName("page_number")
    Integer pageNumber = 1;

    @lombok.Builder.Default
    @SerializedName("page_size")
    Integer pageSize = 20;

    @SerializedName("minimum_activity_date")
    Integer minimumActivityDate;

    @SerializedName("maximum_activity_date")
    Integer maximumActivityDate;

    @lombok.Builder.Default
    @SerializedName("full_result")
    Boolean fullResult = false;

    @Data
    @lombok.Builder
    public static class ActivityParent {
        @SerializedName("id")
        Integer id;

        @SerializedName("type")
        CopperType type;

        public static ActivityParent of(SearchableCopperObject object) {
            return ActivityParent.builder()
                    .id(object.getId())
                    .type(object.getCopperType())
                    .build();
        }
    }

    public static class Builder implements SearchParameters.Builder {
        Integer minimumActivityDate;
        Integer maximumActivityate;

        public Builder minimumActivityDate(Date date) {
            this.minimumActivityDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumActivityDate(Date date) {
            this.maximumActivityate = (int) (date.getTime() / 1000);
            return this;
        }
    }
}
