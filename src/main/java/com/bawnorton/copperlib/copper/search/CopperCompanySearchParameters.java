package com.bawnorton.copperlib.copper.search;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(builderClassName = "Builder")
public class CopperCompanySearchParameters implements SearchParameters {
    @lombok.Builder.Default
    @SerializedName("page_number")
    Integer pageNumber = 1;

    @lombok.Builder.Default
    @SerializedName("page_size")
    Integer pageSize = 20;

    @lombok.Builder.Default
    @SerializedName("sort_by")
    String sortBy = "date_modified";

    @lombok.Builder.Default
    @SerializedName("sort_direction")
    String sortDirection = "asc";

    @SerializedName("name")
    String name;

    @SerializedName("phone_number")
    String phoneNumber;

    @SerializedName("email_domains")
    String email;

    @SerializedName("contact_type_ids")
    List<Integer> contactTypeIds;

    @SerializedName("asignee_ids")
    List<Integer> assigneeIds;
    
    @SerializedName("city")
    String city;

    @SerializedName("state")
    String state;

    @SerializedName("postal_code")
    String postalCode;

    @SerializedName("country")
    String country;

    @SerializedName("tags")
    List<String> tags;

    @SerializedName("socials")
    List<String> socials;

    @SerializedName("followed")
    Integer followed;

    @SerializedName("age")
    Integer age;

    @SerializedName("minimum_interaction_count")
    Integer minimumInteractionCount;

    @SerializedName("maximum_interaction_count")
    Integer maximumInteractionCount;

    @SerializedName("minimum_interaction_date")
    Integer minimumInteractionDate;

    @SerializedName("maximum_interaction_date")
    Integer maximumInteractionDate;

    @SerializedName("minimum_created_date")
    Integer minimumCreationDate;

    @SerializedName("maximum_created_date")
    Integer maximumCreationDate;

    public static class Builder implements SearchParameters.Builder {
        Integer followed;
        Integer minimumInteractionDate;
        Integer maximumInteractionDate;
        Integer minimumCreationDate;
        Integer maximumCreationDate;

        public Builder followed(Boolean followed) {
            this.followed = followed ? 1 : 2;
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

        public Builder minimumCreationDate(Date date) {
            this.minimumCreationDate = (int) (date.getTime() / 1000);
            return this;
        }

        public Builder maximumCreationDate(Date date) {
            this.maximumCreationDate = (int) (date.getTime() / 1000);
            return this;
        }
    }
}
