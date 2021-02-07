package com.inventa.azure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceConfigurationDto {

    private String _id;
    private String name;
    private String label;
    private boolean isActive;

    private AzureProperties properties;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public AzureProperties getProperties() {
        return properties;
    }

    public void setProperties(AzureProperties properties) {
        this.properties = properties;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
