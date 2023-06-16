package com.bawnorton.copperlib.copper;

import com.bawnorton.copperlib.copper.field.AbstractCopperField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperPipeline extends AbstractCopperObject {
    List<CopperPipelineStage> stages;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CopperPipelineStage extends AbstractCopperField {
        Integer id;
        String name;
        Integer pipelinId;
        Integer winProbability;
    }
}
