package com.inventa.azure.dto.nic;

import com.inventa.azure.dto.common.AzureDeviceCommon;

import java.util.List;
import java.util.Map;

public class NetworkInterfaceDto extends AzureDeviceCommon {

    String macAddress;
    List<String> privateIPs;
    List<String> publicIPs;
    List<String> ipConfigs;
    Boolean ipForwarding;
    Boolean acceleratedNetworking;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public List<String> getPrivateIPs() {
        return privateIPs;
    }

    public void setPrivateIPs(List<String> privateIPs) {
        this.privateIPs = privateIPs;
    }

    public List<String> getPublicIPs() {
        return publicIPs;
    }

    public void setPublicIPs(List<String> publicIPs) {
        this.publicIPs = publicIPs;
    }

    public List<String> getIpConfigs() {
        return ipConfigs;
    }

    public void setIpConfigs(List<String> ipConfigs) {
        this.ipConfigs = ipConfigs;
    }

    public Boolean getIpForwarding() {
        return ipForwarding;
    }

    public void setIpForwarding(Boolean ipForwarding) {
        this.ipForwarding = ipForwarding;
    }

    public Boolean getAcceleratedNetworking() {
        return acceleratedNetworking;
    }

    public void setAcceleratedNetworking(Boolean acceleratedNetworking) {
        this.acceleratedNetworking = acceleratedNetworking;
    }
}
