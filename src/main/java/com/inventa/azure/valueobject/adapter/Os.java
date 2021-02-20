package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"type","fullName","name","bits"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Os {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("Bits")
    private String bits;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Full name")
    private String fullName;
    @JsonProperty("Major Release")
    private String majorRelease;
    @JsonProperty("Minor Release")
    private String minorRelease;
    @JsonProperty("Build")
    private String build;
    @JsonProperty("Service Pack")
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

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Os other = (Os) obj;
        return Objects.equals(name, other.getName());
    }

}
