package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.inventa.azure.enums.AdapterEnum;
import com.inventa.azure.enums.AdapterPropertyEnum;

import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {"assetId","name","source","username","name","email","lastSeen","adapterProperty"})
public class UserCommon {


    @JsonProperty("Asset Id")
    private String assetId;

    @JsonProperty("Asset Name")
    private String assetName;

    @JsonProperty("Source")
    private AdapterEnum source;

    @JsonProperty("Username")
    private String username;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Last seen")
    private Date lastSeen;

    @JsonProperty("Adapter Properties")
    private Set<AdapterPropertyEnum> adapterProperty;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public AdapterEnum getSource() {
        return source;
    }

    public void setSource(AdapterEnum source) {
        this.source = source;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Set<AdapterPropertyEnum> getAdapterProperty() {
        return adapterProperty;
    }

    public void setAdapterProperty(Set<AdapterPropertyEnum> adapterProperty) {
        this.adapterProperty = adapterProperty;
    }
}
