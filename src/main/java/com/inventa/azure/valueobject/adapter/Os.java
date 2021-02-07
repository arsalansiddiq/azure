package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"type","fullName","name","bits"})
public class Os {


    @JsonProperty("Type")
    private String type;
    private String bits;
    private String name;
    @JsonProperty("Full name")
    private String fullName;
    private String majorRelease;
    private String minorRelease;
    private String build;
    private String servicePack;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMajorRelease() {
        return majorRelease;
    }

    public void setMajorRelease(String majorRelease) {
        this.majorRelease = majorRelease;
    }

    public String getMinorRelease() {
        return minorRelease;
    }

    public void setMinorRelease(String minorRelease) {
        this.minorRelease = minorRelease;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getServicePack() {
        return servicePack;
    }

    public void setServicePack(String servicePack) {
        this.servicePack = servicePack;
    }

}
