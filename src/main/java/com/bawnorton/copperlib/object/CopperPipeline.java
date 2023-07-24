package com.bawnorton.copperlib.object;

import com.bawnorton.copperlib.object.field.CopperField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperPipeline extends CopperObject {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("stages")
    List<Stage> stages;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Stage extends CopperField {
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
