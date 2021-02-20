package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"name","domain","description","disabled","localAccount"})
public class LocalUser {


    @JsonProperty("Name")
    private String name;
    @JsonProperty("Disabled")
    private Boolean disabled;
    @JsonProperty("Local Account")
    private Boolean localAccount;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Domain")
    private String domain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getLocalAccount() {
        return localAccount;
    }

    public void setLocalAccount(Boolean localAccount) {
        this.localAccount = localAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
