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
@JsonPropertyOrder(value = {"assetId","name","source","adapterDeviceTypeEnum","fetchTime","hostname","ipAddressSet","publicIpSet","privateIpSet","macAddressSet","os","ram","cpus","hardDrives","networkInterfaces","firewallRules","openPorts","installedWindowsUpdates","connectedHardwares","localUsers","sharedFolders","adapterPropertyEnumSet"})
public class Common {

    @JsonProperty("Asset Id")
    private String assetId;

    @JsonProperty("Asset Name")
    private String name;

//    private DeviceTypeEnum type;
    @JsonProperty("Connector Device Type")
    private String adapterDeviceTypeEnum;
//    private AdapterDeviceTypeEnum adapterDeviceTypeEnum;

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
    private List<com.inventa.azure.valueobject.adapter.Cpu> cpus;

    @JsonProperty("OS")
    private com.inventa.azure.valueobject.adapter.Os os;

    @JsonProperty("Hard Drives")
    private List<HardDrive> hardDrives;

    @JsonProperty("Network Interfaces")
    private List<com.inventa.azure.valueobject.adapter.NetworkInterface> networkInterfaces;

    @JsonProperty("Open ports")
    private Set<com.inventa.azure.valueobject.adapter.OpenPorts> openPorts;

    @JsonProperty("Firewall Rules")
    private List<FirewallRule> firewallRules;

    @JsonProperty("Vulnerabilities")
    private List<com.inventa.azure.valueobject.adapter.Vulnerabilities> vulnerabilities;

    @JsonProperty("Installed Softwares")
    private List<com.inventa.azure.valueobject.adapter.InstalledSoftware> installedSoftwares;

    @JsonProperty("Processes")
    private List<com.inventa.azure.valueobject.adapter.Process> processList;

    @JsonProperty("Load Balancer Rules")
    private List<LoadBalancerRules> loadBalancerRules;

    @JsonProperty("DNS Records")
    private List<DnsRecord> dnsRecords;

    @JsonProperty("Local Users")
    private List<LocalUser> localUsers;

    @JsonProperty("Connected Hardware")
    private List<com.inventa.azure.valueobject.adapter.ConnectedHardware> connectedHardwares;

    @JsonProperty("Installed Windows Updates")
    private List<InstalledWindowsUpdate> installedWindowsUpdates;

    @JsonProperty("Shared Folders")
    private List<SharedFolder> sharedFolders;

    @JsonProperty("Last seen")
    private Date fetchTime;

    @JsonProperty("Adapter Properties")
    private Set<AdapterPropertyEnum> adapterPropertyEnumSet;

    @JsonProperty("Public IPs")
    private Set<String> publicIpSet;

    @JsonProperty("Private IPs")
    private Set<String> privateIpSet;

    public List<DnsRecord> getDnsRecords() {
        return dnsRecords;
    }

    public void setDnsRecords(List<DnsRecord> dnsRecords) {
        this.dnsRecords = dnsRecords;
    }

    public List<LoadBalancerRules> getLoadBalancerRules() {
        return loadBalancerRules;
    }

    public void setLoadBalancerRules(List<LoadBalancerRules> loadBalancerRules) {
        this.loadBalancerRules = loadBalancerRules;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public com.inventa.azure.valueobject.adapter.Os getOs() {
        return os;
    }

    public void setOs(com.inventa.azure.valueobject.adapter.Os os) {
        this.os = os;
    }

    public List<HardDrive> getHardDrives() {
        return hardDrives;
    }

    public void setHardDrives(List<HardDrive> hardDrives) {
        this.hardDrives = hardDrives;
    }

    public List<com.inventa.azure.valueobject.adapter.NetworkInterface> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(List<com.inventa.azure.valueobject.adapter.NetworkInterface> networkInterfaces) {
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
//
//    public DeviceTypeEnum getType() {
//        return type;
//    }
//
//    public void setType(DeviceTypeEnum type) {
//        this.type = type;
//    }

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

    public List<com.inventa.azure.valueobject.adapter.Cpu> getCpus() {
        return cpus;
    }

    public void setCpus(List<com.inventa.azure.valueobject.adapter.Cpu> cpus) {
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

    public Set<com.inventa.azure.valueobject.adapter.OpenPorts> getOpenPorts() {
        return openPorts;
    }

    public void setOpenPorts(Set<com.inventa.azure.valueobject.adapter.OpenPorts> openPorts) {
        this.openPorts = openPorts;
    }

    public List<com.inventa.azure.valueobject.adapter.Vulnerabilities> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<com.inventa.azure.valueobject.adapter.Vulnerabilities> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public List<com.inventa.azure.valueobject.adapter.InstalledSoftware> getInstalledSoftwares() {
        return installedSoftwares;
    }

    public void setInstalledSoftwares(List<com.inventa.azure.valueobject.adapter.InstalledSoftware> installedSoftwares) {
        this.installedSoftwares = installedSoftwares;
    }

    public List<com.inventa.azure.valueobject.adapter.Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<com.inventa.azure.valueobject.adapter.Process> processList) {
        this.processList = processList;
    }

    public List<LocalUser> getLocalUsers() {
        return localUsers;
    }

    public void setLocalUsers(List<LocalUser> localUsers) {
        this.localUsers = localUsers;
    }

    public List<com.inventa.azure.valueobject.adapter.ConnectedHardware> getConnectedHardwares() {
        return connectedHardwares;
    }

    public void setConnectedHardwares(List<com.inventa.azure.valueobject.adapter.ConnectedHardware> connectedHardwares) {
        this.connectedHardwares = connectedHardwares;
    }

    public List<InstalledWindowsUpdate> getInstalledWindowsUpdates() {
        return installedWindowsUpdates;
    }

    public void setInstalledWindowsUpdates(List<InstalledWindowsUpdate> installedWindowsUpdates) {
        this.installedWindowsUpdates = installedWindowsUpdates;
    }

    public List<SharedFolder> getSharedFolders() {
        return sharedFolders;
    }

    public void setSharedFolders(List<SharedFolder> sharedFolders) {
        this.sharedFolders = sharedFolders;
    }

    public String getAdapterDeviceTypeEnum() {
        return adapterDeviceTypeEnum;
    }

    public void setAdapterDeviceTypeEnum(String adapterDeviceTypeEnum) {
        this.adapterDeviceTypeEnum = adapterDeviceTypeEnum;
    }

    public Set<String> getPublicIpSet() {
        return publicIpSet;
    }

    public void setPublicIpSet(Set<String> publicIpSet) {
        this.publicIpSet = publicIpSet;
    }

    public Set<String> getPrivateIpSet() {
        return privateIpSet;
    }

    public void setPrivateIpSet(Set<String> privateIpSet) {
        this.privateIpSet = privateIpSet;
    }
//
//    public AdapterDeviceTypeEnum getAdapterDeviceTypeEnum() {
//        return adapterDeviceTypeEnum;
//    }
//
//    public void setAdapterDeviceTypeEnum(AdapterDeviceTypeEnum adapterDeviceTypeEnum) {
//        this.adapterDeviceTypeEnum = adapterDeviceTypeEnum;
//    }

}
