package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;

@JsonPropertyOrder(value = {"manufacturer","ip","mac"})
public class NetworkInterface {


    @JsonProperty("IPs")
    private Set<String> ip;
    @JsonProperty("MAC")
    private String mac;
    @JsonProperty("Manufacturer")
    private String manufacturer;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<String> getIp() {
        return ip;
    }

    public void setIp(Set<String> ip) {
        this.ip = ip;
    }
}
