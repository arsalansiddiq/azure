package com.inventa.azure.valueobject;

import com.inventa.azure.dto.ci.Port;
import com.inventa.azure.dto.nsg.AssociatedAsset;
import com.inventa.azure.dto.nsg.FirewallRule;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.enums.ParentCategoryEnum;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Common {

    private String identifier;
    private String hostName;
    private String assetName;

    private Set<String> macAddress;
    private Set<String> ipAddress;
    private List<FirewallRule> firewallRules;
    private List<AssociatedAsset> associations;
    private List<Port> ports;
    private List<Map> assetTags;

    private DeviceTypeEnum type;
    private Set<String> adapterProperties;
    private List<String> source;

    private Date fetchTime;

    private Map<String,Object> summary;
    private ParentCategoryEnum parentType;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Set<String> getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(Set<String> macAddress) {
        this.macAddress = macAddress;
    }

    public Set<String> getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Set<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<FirewallRule> getFirewallRules() {
        return firewallRules;
    }

    public void setFirewallRules(List<FirewallRule> firewallRules) {
        this.firewallRules = firewallRules;
    }

    public List<AssociatedAsset> getAssociations() {
        return associations;
    }

    public void setAssociations(List<AssociatedAsset> associations) {
        this.associations = associations;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<Map> getAssetTags() {
        return assetTags;
    }

    public void setAssetTags(List<Map> assetTags) {
        this.assetTags = assetTags;
    }

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public Set<String> getAdapterProperties() {
        return adapterProperties;
    }

    public void setAdapterProperties(Set<String> adapterProperties) {
        this.adapterProperties = adapterProperties;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }

    public Map<String, Object> getSummary() {
        return summary;
    }

    public void setSummary(Map<String, Object> summary) {
        this.summary = summary;
    }

    public ParentCategoryEnum getParentType() {
        return parentType;
    }

    public void setParentType(ParentCategoryEnum parentType) {
        this.parentType = parentType;
    }
}
