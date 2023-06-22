package com.bawnorton.copperlib.object.object;

import com.bawnorton.copperlib.object.field.AbstractCopperField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperPipeline extends AbstractCopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("stages")
    List<Stage> stages;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Stage extends AbstractCopperField {
        @SerializedName("id")
        Integer id;

        @SerializedName("name")
        String name;

        @SerializedName("pipeline_id")
        Integer pipelinId;

        @SerializedName("win_probability")
        Integer winProbability;
    }
}
