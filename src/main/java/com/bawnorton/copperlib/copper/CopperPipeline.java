package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.AbstractCopperField;
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
    List<CopperPipelineStage> stages;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CopperPipelineStage extends AbstractCopperField {
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
