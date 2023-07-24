package com.bawnorton.copperlib.object.field;

import com.bawnorton.copperlib.gson.ForceListTypeAdpaterFactory;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CopperCustomField extends CopperField {

    @SerializedName("custom_field_definition_id")
    Integer customFieldDefinitionId;

    @JsonAdapter(ForceListTypeAdpaterFactory.class)
    @SerializedName("value")
    List<String> values;
}
