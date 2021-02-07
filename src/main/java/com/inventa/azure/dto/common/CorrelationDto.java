package com.inventa.azure.dto.common;

import com.inventa.azure.enums.DeviceTypeEnum;

import java.util.Set;

public class CorrelationDto {


    private String identifier;
    private DeviceTypeEnum type;

    private Set<String> macAddresses;
    private Set<String> ipAddresses;
    private String hostname;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


    public Set<String> getMacAddresses() {
        return macAddresses;
    }

    public void setMacAddresses(Set<String> macAddresses) {
        this.macAddresses = macAddresses;
    }

    public Set<String> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(Set<String> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

}
