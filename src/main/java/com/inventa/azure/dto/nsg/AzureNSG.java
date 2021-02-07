package com.inventa.azure.dto.nsg;

import com.azure.resourcemanager.network.fluent.models.NetworkSecurityGroupInner;
import com.azure.resourcemanager.network.fluent.models.SubnetInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inventa.azure.dto.common.AzureTagsTab;

import java.util.List;

public class AzureNSG {

    String id;
    String name;
    String subscriptionId;
    String subscriptionName;
    String resourceGroupName;
    String location;
    String type;
    Boolean isAssociatedWithSubnet;
    Boolean isAssociatedWithNI;
    Integer nsgAssociatedSubnetsCount;
    Integer nsgAssociatedNetworkInterfacesCount;
    String tags;
    List<AzureTagsTab> tagsTabs;
    List<SubnetInner> subnet;
    List<AssociatedAsset> associatedAssets;
    List<FirewallRule> nsgRules;
    NetworkSecurityGroupInner networkSecurityGroupInner;
//    List<AzureRoleAssignments> azureRoleAssignments;
    private String instanceAssociationId;

    @JsonProperty("Network Security Group::Is Associated With Subnet")
    public Boolean getAssociatedWithSubnet() {
        return isAssociatedWithSubnet;
    }

    public void setAssociatedWithSubnet(Boolean associatedWithSubnet) {
        isAssociatedWithSubnet = associatedWithSubnet;
    }

    @JsonProperty("Network Security Group::Is Associated With Network Interface")
    public Boolean getAssociatedWithNI() {
        return isAssociatedWithNI;
    }

    public void setAssociatedWithNI(Boolean associatedWithNI) {
        isAssociatedWithNI = associatedWithNI;
    }

    @JsonProperty("Network Security Group::Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("Network Security Group::Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Resource Group Name")
    public String getResourceGroupName() {
        return resourceGroupName;
    }

    public void setResourceGroupName(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
    }

    @JsonProperty("Subscription Name")
    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    @JsonProperty("Subscription Id")
    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @JsonProperty("Network Security Group::Subnet List")
    public List<SubnetInner> getSubnet() {
        return subnet;
    }

    public void setSubnet(List<SubnetInner> subnet) {
        this.subnet = subnet;
    }

    @JsonProperty("Network Security Group::Inner")
    public NetworkSecurityGroupInner getNetworkSecurityGroupInner() {
        return networkSecurityGroupInner;
    }

    public void setNetworkSecurityGroupInner(NetworkSecurityGroupInner networkSecurityGroupInner) {
        this.networkSecurityGroupInner = networkSecurityGroupInner;
    }

//    @JsonProperty("Role Assignments")
//    public List<AzureRoleAssignments> getAzureRoleAssignments() {
//        return azureRoleAssignments;
//    }
//
//    public void setAzureRoleAssignments(List<AzureRoleAssignments> azureRoleAssignments) {
//        this.azureRoleAssignments = azureRoleAssignments;
//    }

    @JsonProperty("Network Security Group::Associated Subnet Count")
    public Integer getNsgAssociatedSubnetsCount() {
        return nsgAssociatedSubnetsCount;
    }

    public void setNsgAssociatedSubnetsCount(Integer nsgAssociatedSubnetsCount) {
        this.nsgAssociatedSubnetsCount = nsgAssociatedSubnetsCount;
    }

    @JsonProperty("Network Security Group::Associated Network Interface Count")
    public Integer getNsgAssociatedNetworkInterfacesCount() {
        return nsgAssociatedNetworkInterfacesCount;
    }

    public void setNsgAssociatedNetworkInterfacesCount(Integer nsgAssociatedNetworkInterfacesCount) {
        this.nsgAssociatedNetworkInterfacesCount = nsgAssociatedNetworkInterfacesCount;
    }

    @JsonProperty("Tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @JsonProperty("Tags Tab")
    public List<AzureTagsTab> getTagsTabs() {
        return tagsTabs;
    }

    public void setTagsTabs(List<AzureTagsTab> tagsTabs) {
        this.tagsTabs = tagsTabs;
    }

    @JsonProperty("Attached To")
    public List<AssociatedAsset> getAssociatedAssets() {
        return associatedAssets;
    }

    public void setAssociatedAssets(List<AssociatedAsset> associatedAssets) {
        this.associatedAssets = associatedAssets;
    }

    @JsonProperty("firewallRules")
    public List<FirewallRule> getNsgRules() {
        return nsgRules;
    }

    public void setNsgRules(List<FirewallRule> nsgRules) {
        this.nsgRules = nsgRules;
    }

    public String getInstanceAssociationId() {
        return instanceAssociationId;
    }

    public void setInstanceAssociationId(String instanceAssociationId) {
        this.instanceAssociationId = instanceAssociationId;
    }
}
