package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.inventa.azure.enums.AdapterEnum;
import com.inventa.azure.enums.AdapterPropertyEnum;

import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {"assetId","name","source","adapterDeviceTypeEnum","fetchTime","hostname","ipAddressSet","macAddressSet","os","ram","cpus","hardDrives","networkInterfaces","firewallRules","openPorts","adapterPropertyEnumSet"})
public class Common {

    @JsonProperty("Asset Id")
    private String assetId;

    @JsonProperty("Asset Name")
    private String name;

//    @JsonProperty("Device Type")
//    private DeviceTypeEnum type;
    @JsonProperty("Connector Device Type")
    private String adapterDeviceTypeEnum;

    @JsonProperty("Asset Source")
    private AdapterEnum source;

    @JsonProperty("Hostname")
    private String hostname;

    @JsonProperty("IPs")
    private Set<String> ipAddressSet;

    @JsonProperty("MACs")
    private Set<String> macAddressSet;

    @JsonProperty("RAM")
    private Ram ram;

    @JsonProperty("CPUs")
    private List<Cpu> cpus;

    @JsonProperty("OS")
    private Os os;

    @JsonProperty("Hard Drives")
    private List<HardDrive> hardDrives;

    @JsonProperty("Network Interfaces")
    private List<NetworkInterface> networkInterfaces;

    @JsonProperty("Open ports")
    private Set<String> openPorts;

    @JsonProperty("Firewall Rules")
    private List<FirewallRule> firewallRules;

    @JsonProperty("Last seen")
    private Date fetchTime;

    @JsonProperty("Adapter Properties")
    private Set<AdapterPropertyEnum> adapterPropertyEnumSet;



    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Os getOs() {
        return os;
    }

    public void setOs(Os os) {
        this.os = os;
    }

    public List<HardDrive> getHardDrives() {
        return hardDrives;
    }

    public void setHardDrives(List<HardDrive> hardDrives) {
        this.hardDrives = hardDrives;
    }

    public List<NetworkInterface> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(List<NetworkInterface> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdapterEnum getSource() {
        return source;
    }

    public void setSource(AdapterEnum source) {
        this.source = source;
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }

    public Set<AdapterPropertyEnum> getAdapterPropertyEnumSet() {
        return adapterPropertyEnumSet;
    }

    public void setAdapterPropertyEnumSet(Set<AdapterPropertyEnum> adapterPropertyEnumSet) {
        this.adapterPropertyEnumSet = adapterPropertyEnumSet;
    }

    public List<FirewallRule> getFirewallRules() {
        return firewallRules;
    }

    public void setFirewallRules(List<FirewallRule> firewallRules) {
        this.firewallRules = firewallRules;
    }

    public List<Cpu> getCpus() {
        return cpus;
    }

    public void setCpus(List<Cpu> cpus) {
        this.cpus = cpus;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Set<String> getIpAddressSet() {
        return ipAddressSet;
    }

    public void setIpAddressSet(Set<String> ipAddressSet) {
        this.ipAddressSet = ipAddressSet;
    }

    public Set<String> getMacAddressSet() {
        return macAddressSet;
    }

    public void setMacAddressSet(Set<String> macAddressSet) {
        this.macAddressSet = macAddressSet;
    }

    public Set<String> getOpenPorts() {
        return openPorts;
    }

    public void setOpenPorts(Set<String> openPorts) {
        this.openPorts = openPorts;
    }

    public String getAdapterDeviceTypeEnum() {
        return adapterDeviceTypeEnum;
    }

    public void setAdapterDeviceTypeEnum(String adapterDeviceTypeEnum) {
        this.adapterDeviceTypeEnum = adapterDeviceTypeEnum;
    }
}
