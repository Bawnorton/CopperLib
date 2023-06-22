package com.bawnorton.copperlib.object.search;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(builderClassName = "Builder")
public class CopperTaskSearchParameters implements SearchParameters {
    @lombok.Builder.Default
    @SerializedName("page_number")
    Integer pageNumber = 1;

    @lombok.Builder.Default
    @SerializedName("page_size")
    Integer pageSize = 20;

    @lombok.Builder.Default
    @SerializedName("sort_by")
    String sortBy = "due_date";

    @lombok.Builder.Default
    @SerializedName("sort_direction")
    String sortDirection = "asc";

    @SerializedName("asignee_ids")
    List<Integer> assigneeIds;
    
    @SerializedName("opportunity_ids")
    List<Integer> opportunityIds;

    @SerializedName("project_ids")
    List<Integer> projectIds;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("followed")
    Integer followed;

    @SerializedName("minimum_due_date")
    Integer minimumDueDate;

    @SerializedName("maximum_due_date")
    Integer maximumDueDate;

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
        Integer minimumDueDate;
        Integer maximumDueDate;
        Integer minimumCreationDate;
        Integer maximumCreationDate;
        Integer minimumModifiedDate;
        Integer maximumModifiedDate;

        public Builder followed(Boolean followed) {
            this.followed = followed ? 1 : 2;
            return this;
        }

        public Builder minimumDueDate(Date date) {
            this.minimumDueDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumDueDate(Date date) {
            this.maximumDueDate = (int) (date.getTime() / 1000);
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
