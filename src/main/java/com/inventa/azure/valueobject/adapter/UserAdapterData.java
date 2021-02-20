package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"common","specific"})
public class UserAdapterData {

//    private Map query;
    @JsonProperty("Asset Id")
    private String assetId;
    @JsonProperty("Common")
    private com.inventa.azure.valueobject.adapter.UserCommon common;
    @JsonProperty("Specific")
    private LinkedHashMap specific;
    private Map query;

    public com.inventa.azure.valueobject.adapter.UserCommon getCommon() {
        return common;
    }

    public void setCommon(com.inventa.azure.valueobject.adapter.UserCommon common) {
        this.common = common;
    }

    public LinkedHashMap getSpecific() {
        return specific;
    }

    public void setSpecific(LinkedHashMap specific) {
        this.specific = specific;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Map getQuery() {
        return query;
    }

    public void setQuery(Map query) {
        this.query = query;
    }


}
