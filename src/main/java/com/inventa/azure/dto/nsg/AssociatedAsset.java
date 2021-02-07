package com.inventa.azure.dto.nsg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociatedAsset {

    String associatedEntityType;
    String name;
    String address;
    String nic;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("Associated Entity Type")
    public String getAssociatedEntityType() {
        return associatedEntityType;
    }

    public void setAssociatedEntityType(String associatedEntityType) {
        this.associatedEntityType = associatedEntityType;
    }

    @JsonProperty("NIC")
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
