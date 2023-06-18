package com.bawnorton.copperlib.copper.search;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(builderClassName = "Builder")
public class CopperOpportunitySearchParameters implements SearchParameters {
    @lombok.Builder.Default
    @SerializedName("page_number")
    Integer pageNumber = 1;

    @lombok.Builder.Default
    @SerializedName("page_size")
    Integer pageSize = 20;

    @lombok.Builder.Default
    @SerializedName("sort_by")
    String sortBy = "name";

    @lombok.Builder.Default
    @SerializedName("sort_direction")
    String sortDirection = "asc";

    @SerializedName("name")
    String name;

    @SerializedName("asignee_ids")
    List<Integer> assigneeIds;

    @SerializedName("status_ids")
    List<Integer> statusIds;

    @SerializedName("pipeline_ids")
    List<Integer> pipelineIds;

    @SerializedName("pipeline_stage_ids")
    List<Integer> pipelineStageIds;

    @SerializedName("priority_ids")
    List<Integer> priorityIds;

    @SerializedName("customer_source_ids")
    List<Integer> customerSourceIds;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("followed")
    Integer followed;

    @SerializedName("age")
    Integer age;

    @SerializedName("minimum_monetary_value")
    Integer minimumMonetaryValue;

    @SerializedName("maximum_monetary_value")
    Integer maximumMonetaryValue;

    @SerializedName("minimum_interaction_count")
    Integer minimumInteractionCount;

    @SerializedName("maximum_interaction_count")
    Integer maximumInteractionCount;

    @SerializedName("minimum_close_date")
    Integer minimumCloseDate;

    @SerializedName("maximum_close_date")
    Integer maximumCloseDate;

    @SerializedName("minimum_interaction_date")
    Integer minimumInteractionDate;

    @SerializedName("maximum_interaction_date")
    Integer maximumInteractionDate;

    @SerializedName("minimum_stage_changed_date")
    Integer minimumStageChangedDate;

    @SerializedName("maximum_stage_changed_date")
    Integer maximumStageChangedDate;

    @SerializedName("minimum_created_date")
    Integer minimumCreationDate;

    @SerializedName("maximum_created_date")
    Integer maximumCreationDate;

    @SerializedName("minimum_modified_date")
    Integer minimumModifiedDate;

    @SerializedName("maximum_modified_date")
    Integer maximumModifiedDate;

    public static class Builder implements SearchParameters.Builder {
        Integer followed;
        Integer minimumCloseDate;
        Integer maximumCloseDate;
        Integer minimumInteractionDate;
        Integer maximumInteractionDate;
        Integer minimumStageChangedDate;
        Integer maximumStageChangedDate;
        Integer minimumCreationDate;
        Integer maximumCreationDate;
        Integer minimumModifiedDate;
        Integer maximumModifiedDate;

        public Builder followed(Boolean followed) {
            this.followed = followed ? 1 : 2;
            return this;
        }

        public Builder minimumCloseDate(Date date) {
            this.minimumCloseDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumCloseDate(Date date) {
            this.maximumCloseDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder minimumInteractionDate(Date date) {
            this.minimumInteractionDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumInteractionDate(Date date) {
            this.maximumInteractionDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder minimumStageChangedDate(Date date) {
            this.minimumStageChangedDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumStageChangedDate(Date date) {
            this.maximumStageChangedDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder minimumCreationDate(Date date) {
            this.minimumCreationDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumCreationDate(Date date) {
            this.maximumCreationDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder minimumModifiedDate(Date date) {
            this.minimumModifiedDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumModifiedDate(Date date) {
            this.maximumModifiedDate = (int) (date.getTime() / 1000);
            return this;
        }
    }
}
