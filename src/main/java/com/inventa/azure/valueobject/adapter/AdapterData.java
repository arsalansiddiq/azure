package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonPropertyOrder(value = {"Commmon","Specific","assetId","query"})
public class AdapterData {

//    private Map query;
    @JsonProperty("Asset Id")
    private String assetId;
    @JsonProperty("Common")
    private Common common;
    @JsonProperty("Specific")
    private LinkedHashMap specific;
    private Map query;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
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
