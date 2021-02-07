package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"name","manufacturer","coreCount","threadsCount","type","hyperThreading" })
public class Cpu {

    private String manufacturer;
    @JsonProperty("Name")
    private String name;
    private Long speed;
    private String type;
    @JsonProperty("Core Count")
    private Long coreCount;
    @JsonProperty("Threads Count")
    private Long threadsCount;
    @JsonProperty("Hyper Threading")
    private Boolean hyperThreading;


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Long coreCount) {
        this.coreCount = coreCount;
    }

    public Long getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(Long threadsCount) {
        this.threadsCount = threadsCount;
    }

    public Boolean getHyperThreading() {
        return hyperThreading;
    }

    public void setHyperThreading(Boolean hyperThreading) {
        this.hyperThreading = hyperThreading;
    }
}
