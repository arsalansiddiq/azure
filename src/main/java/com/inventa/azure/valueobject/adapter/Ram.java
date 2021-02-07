package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"totalRam","availableRam"})
public class Ram {

    @JsonProperty("Total RAM")
    private Long totalRam;

    @JsonProperty("Available RAM")
    private Long availableRam;

    public Long getTotalRam() {
        return totalRam;
    }

    public void setTotalRam(Long totalRam) {
        this.totalRam = totalRam;
    }

    public Long getAvailableRam() {
        return availableRam;
    }

    public void setAvailableRam(Long availableRam) {
        this.availableRam = availableRam;
    }
}
