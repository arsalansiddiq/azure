package com.inventa.azure.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AzureTagsTab {

    String key;
    String value;

    @JsonProperty("Key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
