package com.bawnorton.copperlib.object.field;

import com.bawnorton.copperlib.gson.ForceListTypeAdpaterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CopperCustomField extends AbstractCopperField {
    @SerializedName("custom_field_definition_id")
    public Integer customFieldDefinitionId;

    @JsonAdapter(ForceListTypeAdpaterFactory.class)
    @SerializedName("value")
    public List<String> values;
}
