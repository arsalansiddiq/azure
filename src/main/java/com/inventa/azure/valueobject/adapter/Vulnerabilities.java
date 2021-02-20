package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"connector","vulnerabilityNumber","softwareName","softwareVendor","cvsVersion","cvsScore","cvsSeverity","name","links"})
public class Vulnerabilities {

    @JsonProperty("Vulnerability Number")
    private String vulnerabilityNumber;

    @JsonProperty("Software Name")
    private String softwareName;

    @JsonProperty("Software Vendor")
    private String softwareVendor;

    @JsonProperty("CVS Version")
    private Integer cvsVersion;

    @JsonProperty("CVSS Score")
    private Float cvssScore;

    @JsonProperty("CVS Severity")
    private String cvsSeverity;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Links")
    private String links;

    public String getVulnerabilityNumber() {
        return vulnerabilityNumber;
    }

    public void setVulnerabilityNumber(String vulnerabilityNumber) {
        this.vulnerabilityNumber = vulnerabilityNumber;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getSoftwareVendor() {
        return softwareVendor;
    }

    public void setSoftwareVendor(String softwareVendor) {
        this.softwareVendor = softwareVendor;
    }



    public String getCvsSeverity() {
        return cvsSeverity;
    }

    public void setCvsSeverity(String cvsSeverity) {
        this.cvsSeverity = cvsSeverity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
    public Integer getCvsVersion() {
        return cvsVersion;
    }

    public void setCvsVersion(Integer cvsVersion) {
        this.cvsVersion = cvsVersion;
    }

    public Float getCvssScore() {
        return cvssScore;
    }

    public void setCvssScore(Float cvssScore) {
        this.cvssScore = cvssScore;
    }
}
