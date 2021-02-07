package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"name","totalSize","freeSize","type"})
public class HardDrive {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Total size")
    private Long totalSize;

    @JsonProperty("Free size")
    private Long freeSize;

    @JsonProperty("Type")
    private String type;

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public Long getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(Long freeSize) {
        this.freeSize = freeSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
