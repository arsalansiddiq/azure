package com.inventa.azure.dto;

public class AzureProperties {

    private String subscriptionId;
    private String tenantId;
    private String clientId;
    private String clientSecret;
    private String accountTag;
    private Boolean subscriptions = false;

    private Boolean users = true;
    private Boolean uGroups = true;
    private Boolean virtualMachines = true;
    private Boolean vmRoleAssignments;
    private Boolean applicationGateways;
    private Boolean applicationGateways_roleAssignments;
    private Boolean publicIPAddresses;
    private Boolean publicIPAddresses_roleAssignments;
    private Boolean virtualNetworks;
    private Boolean virtualNetworks_roleAssignments;
    private Boolean storageAccounts;
    private Boolean storageAccounts_roleAssignments;
    private Boolean loadBalancers;
    private Boolean loadBalancers_roleAssignments;
    private Boolean trafficManagerProfiles;
    private Boolean trafficManagerProfiles_roleAssignments;
    private Boolean networkSecurityGroups;
    private Boolean networkSecurityGroups_roleAssignments;
    private Boolean containers;
    private Boolean container_roleAssignments;
    private Boolean publicDNSZones;
    private Boolean publicDNSZones_roleAssignments;
    private Boolean privateDNSZones;
    private Boolean privateDNSZones_roleAssignments;
    private Boolean saBlobs;
    private Boolean saFileShares;
    private Boolean saQueues;
    private Boolean saTables;
    private boolean httpsProxyEnable;
    private String httpsProxy;
    private Integer httpsProxyPort;
    private String httpsProxyUser;
    private String httpsProxyPassword;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public boolean getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(boolean subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getAccountTag() {
        return accountTag;
    }

    public void setAccountTag(String accountTag) {
        this.accountTag = accountTag;
    }

    public boolean isUsers() {
        return users;
    }

    public void setUsers(boolean users) {
        this.users = users;
    }

    public boolean isuGroups() {
        return uGroups;
    }

    public void setuGroups(boolean uGroups) {
        this.uGroups = uGroups;
    }

    public boolean isVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(boolean virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

    public boolean isVmRoleAssignments() {
        return vmRoleAssignments;
    }

    public void setVmRoleAssignments(boolean vmRoleAssignments) {
        this.vmRoleAssignments = vmRoleAssignments;
    }

    public boolean isApplicationGateways() {
        return applicationGateways;
    }

    public void setApplicationGateways(boolean applicationGateways) {
        this.applicationGateways = applicationGateways;
    }

    public boolean isApplicationGateways_roleAssignments() {
        return applicationGateways_roleAssignments;
    }

    public void setApplicationGateways_roleAssignments(boolean applicationGateways_roleAssignments) {
        this.applicationGateways_roleAssignments = applicationGateways_roleAssignments;
    }

    public boolean isPublicIPAddresses() {
        return publicIPAddresses;
    }

    public void setPublicIPAddresses(boolean publicIPAddresses) {
        this.publicIPAddresses = publicIPAddresses;
    }

    public boolean isPublicIPAddresses_roleAssignments() {
        return publicIPAddresses_roleAssignments;
    }

    public void setPublicIPAddresses_roleAssignments(boolean publicIPAddresses_roleAssignments) {
        this.publicIPAddresses_roleAssignments = publicIPAddresses_roleAssignments;
    }

    public boolean isVirtualNetworks() {
        return virtualNetworks;
    }

    public void setVirtualNetworks(boolean virtualNetworks) {
        this.virtualNetworks = virtualNetworks;
    }

    public boolean isVirtualNetworks_roleAssignments() {
        return virtualNetworks_roleAssignments;
    }

    public void setVirtualNetworks_roleAssignments(boolean virtualNetworks_roleAssignments) {
        this.virtualNetworks_roleAssignments = virtualNetworks_roleAssignments;
    }

    public boolean isStorageAccounts() {
        return storageAccounts;
    }

    public void setStorageAccounts(boolean storageAccounts) {
        this.storageAccounts = storageAccounts;
    }

    public boolean isStorageAccounts_roleAssignments() {
        return storageAccounts_roleAssignments;
    }

    public void setStorageAccounts_roleAssignments(boolean storageAccounts_roleAssignments) {
        this.storageAccounts_roleAssignments = storageAccounts_roleAssignments;
    }

    public boolean isLoadBalancers() {
        return loadBalancers;
    }

    public void setLoadBalancers(boolean loadBalancers) {
        this.loadBalancers = loadBalancers;
    }

    public boolean isLoadBalancers_roleAssignments() {
        return loadBalancers_roleAssignments;
    }

    public void setLoadBalancers_roleAssignments(boolean loadBalancers_roleAssignments) {
        this.loadBalancers_roleAssignments = loadBalancers_roleAssignments;
    }

    public boolean isTrafficManagerProfiles() {
        return trafficManagerProfiles;
    }

    public void setTrafficManagerProfiles(boolean trafficManagerProfiles) {
        this.trafficManagerProfiles = trafficManagerProfiles;
    }

    public boolean isTrafficManagerProfiles_roleAssignments() {
        return trafficManagerProfiles_roleAssignments;
    }

    public void setTrafficManagerProfiles_roleAssignments(boolean trafficManagerProfiles_roleAssignments) {
        this.trafficManagerProfiles_roleAssignments = trafficManagerProfiles_roleAssignments;
    }

    public boolean isNetworkSecurityGroups() {
        return networkSecurityGroups;
    }

    public void setNetworkSecurityGroups(boolean networkSecurityGroups) {
        this.networkSecurityGroups = networkSecurityGroups;
    }

    public boolean isNetworkSecurityGroups_roleAssignments() {
        return networkSecurityGroups_roleAssignments;
    }

    public void setNetworkSecurityGroups_roleAssignments(boolean networkSecurityGroups_roleAssignments) {
        this.networkSecurityGroups_roleAssignments = networkSecurityGroups_roleAssignments;
    }

    public boolean isContainers() {
        return containers;
    }

    public void setContainers(boolean containers) {
        this.containers = containers;
    }

    public boolean isContainer_roleAssignments() {
        return container_roleAssignments;
    }

    public void setContainer_roleAssignments(boolean container_roleAssignments) {
        this.container_roleAssignments = container_roleAssignments;
    }

    public boolean isPublicDNSZones() {
        return publicDNSZones;
    }

    public void setPublicDNSZones(boolean publicDNSZones) {
        this.publicDNSZones = publicDNSZones;
    }

    public boolean isPublicDNSZones_roleAssignments() {
        return publicDNSZones_roleAssignments;
    }

    public void setPublicDNSZones_roleAssignments(boolean publicDNSZones_roleAssignments) {
        this.publicDNSZones_roleAssignments = publicDNSZones_roleAssignments;
    }

    public boolean isPrivateDNSZones() {
        return privateDNSZones;
    }

    public void setPrivateDNSZones(boolean privateDNSZones) {
        this.privateDNSZones = privateDNSZones;
    }

    public boolean isPrivateDNSZones_roleAssignments() {
        return privateDNSZones_roleAssignments;
    }

    public void setPrivateDNSZones_roleAssignments(boolean privateDNSZones_roleAssignments) {
        this.privateDNSZones_roleAssignments = privateDNSZones_roleAssignments;
    }

    public boolean isSaBlobs() {
        return saBlobs;
    }

    public void setSaBlobs(boolean saBlobs) {
        this.saBlobs = saBlobs;
    }

    public boolean isSaFileShares() {
        return saFileShares;
    }

    public void setSaFileShares(boolean saFileShares) {
        this.saFileShares = saFileShares;
    }

    public boolean isSaQueues() {
        return saQueues;
    }

    public void setSaQueues(boolean saQueues) {
        this.saQueues = saQueues;
    }

    public boolean isSaTables() {
        return saTables;
    }

    public void setSaTables(boolean saTables) {
        this.saTables = saTables;
    }

    public boolean isHttpsProxyEnable() {
        return httpsProxyEnable;
    }

    public void setHttpsProxyEnable(boolean httpsProxyEnable) {
        this.httpsProxyEnable = httpsProxyEnable;
    }

    public String getHttpsProxy() {
        return httpsProxy;
    }

    public void setHttpsProxy(String httpsProxy) {
        this.httpsProxy = httpsProxy;
    }

    public Integer getHttpsProxyPort() {
        return httpsProxyPort;
    }

    public void setHttpsProxyPort(Integer httpsProxyPort) {
        this.httpsProxyPort = httpsProxyPort;
    }

    public String getHttpsProxyUser() {
        return httpsProxyUser;
    }

    public void setHttpsProxyUser(String httpsProxyUser) {
        this.httpsProxyUser = httpsProxyUser;
    }

    public String getHttpsProxyPassword() {
        return httpsProxyPassword;
    }

    public void setHttpsProxyPassword(String httpsProxyPassword) {
        this.httpsProxyPassword = httpsProxyPassword;
    }

}
