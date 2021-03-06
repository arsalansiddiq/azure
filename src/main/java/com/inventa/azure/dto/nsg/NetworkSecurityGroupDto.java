package com.inventa.azure.dto.nsg;

import com.inventa.azure.dto.common.AzureDeviceCommon;

import java.util.List;
import java.util.Map;

public class NetworkSecurityGroupDto extends AzureDeviceCommon {

    Boolean isAssociatedWithSubnet;
    Boolean isAssociatedWithNI;
    Integer nsgAssociatedSubnetsCount;
    Integer nsgAssociatedNetworkInterfacesCount;
    List<AssociatedAsset> associatedAssets;
    List<FirewallRule> firewallRules;

    public Boolean getAssociatedWithSubnet() {
        return isAssociatedWithSubnet;
    }

    public void setAssociatedWithSubnet(Boolean associatedWithSubnet) {
        isAssociatedWithSubnet = associatedWithSubnet;
    }

    public Boolean getAssociatedWithNI() {
        return isAssociatedWithNI;
    }

    public void setAssociatedWithNI(Boolean associatedWithNI) {
        isAssociatedWithNI = associatedWithNI;
    }

    public Integer getNsgAssociatedSubnetsCount() {
        return nsgAssociatedSubnetsCount;
    }

    public void setNsgAssociatedSubnetsCount(Integer nsgAssociatedSubnetsCount) {
        this.nsgAssociatedSubnetsCount = nsgAssociatedSubnetsCount;
    }

    public Integer getNsgAssociatedNetworkInterfacesCount() {
        return nsgAssociatedNetworkInterfacesCount;
    }

    public void setNsgAssociatedNetworkInterfacesCount(Integer nsgAssociatedNetworkInterfacesCount) {
        this.nsgAssociatedNetworkInterfacesCount = nsgAssociatedNetworkInterfacesCount;
    }

    public List<AssociatedAsset> getAssociatedAssets() {
        return associatedAssets;
    }

    public void setAssociatedAssets(List<AssociatedAsset> associatedAssets) {
        this.associatedAssets = associatedAssets;
    }

    public List<FirewallRule> getFirewallRules() {
        return firewallRules;
    }

    public void setFirewallRules(List<FirewallRule> firewallRules) {
        this.firewallRules = firewallRules;
    }
}
