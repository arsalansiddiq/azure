package com.inventa.azure.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.common.AzureClient;
import com.inventa.azure.common.AzureUtils;
import com.inventa.azure.converter.NetworkSecurityGroupConverter;
import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.nsg.AssociatedAsset;
import com.inventa.azure.dto.nsg.FirewallRule;
import com.inventa.azure.dto.nsg.NetworkSecurityGroupDto;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.service.NetworkSecurityGroupService;
import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.network.NetworkSecurityGroup;
import com.microsoft.azure.management.network.implementation.NetworkManager;
import com.microsoft.azure.management.resources.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NetworkSecurityGroupServiceImpl implements NetworkSecurityGroupService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AzureClient azureClient;

    @Autowired
    DeviceServiceImpl deviceService;

    @Autowired
    NetworkSecurityGroupConverter securityGroupConverter;

    @Override
    public List<Map> discoverNetworkSecurityGroups(String instanceId, AzureProperties azureProperties) {

        List<Map> networkSecurityGroups = new ArrayList<>();

        try {

            if(azureProperties!=null) {

                if (azureProperties.getSubscriptions()) {

                    PagedList<Subscription> subscriptions = Azure.authenticate(azureClient.getAzureClient(
                            azureProperties.getClientId(), azureProperties.getClientSecret(), azureProperties.getTenantId())).subscriptions().list();

                    subscriptions.forEach(x -> {
                        Azure azure = Azure.authenticate(azureClient.getAzureClient(
                                azureProperties.getClientId(),
                                azureProperties.getClientSecret(),
                                azureProperties.getTenantId())).withSubscription(x.subscriptionId());

                        networkSecurityGroups.addAll(fetchNetworkSecurityGroups(azure, "Text, test", instanceId));
                    });
                } else {
                    Azure azure = Azure.authenticate(azureClient.getAzureClient(
                            azureProperties.getClientId(), azureProperties.getClientSecret(), azureProperties.getTenantId())).withSubscription(azureProperties.getSubscriptionId());
                    networkSecurityGroups.addAll(fetchNetworkSecurityGroups(azure, "Text, test", instanceId));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkSecurityGroups;
    }

    private List<Map> fetchNetworkSecurityGroups(Azure azure, String accountTag, String instanceId) {
        List<Map> networkSecurityGroups = new ArrayList<>();

        PagedList<NetworkSecurityGroup> nsgs = azure.networkSecurityGroups().list();

        if (nsgs != null && !nsgs.isEmpty()) {
            nsgs.forEach(x -> {
                try {
                    networkSecurityGroups.add(objectMapper.convertValue(formulateNetworkSecurityGroup(x, azure.getCurrentSubscription(), accountTag, instanceId), Map.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        deviceService.add(networkSecurityGroups, securityGroupConverter);
        return networkSecurityGroups;
    }

    private NetworkSecurityGroupDto formulateNetworkSecurityGroup(NetworkSecurityGroup nsg, Subscription subscription, String accountTag, String instanceId) {

        NetworkSecurityGroupDto networkSecurityGroupDto = new NetworkSecurityGroupDto();

        try {

            if (instanceId!=null && !instanceId.isEmpty()) networkSecurityGroupDto.setInstanceAssociationId(instanceId);

            networkSecurityGroupDto.setAssetName(nsg.name());
            networkSecurityGroupDto.setIdentifier(nsg.id());
            networkSecurityGroupDto.setType(DeviceTypeEnum.NETWORK_SECURITY_GROUP);
            networkSecurityGroupDto.setSubscriptionId(subscription.subscriptionId());
            networkSecurityGroupDto.setSubscriptionName(subscription.displayName());
            networkSecurityGroupDto.setResourceGroupName(nsg.resourceGroupName());
            networkSecurityGroupDto.setLocation(nsg.region() != null ? nsg.region().label() : null);
            networkSecurityGroupDto.setTags(AzureUtils.getTabTags(nsg.tags(), accountTag));

            List<List<com.microsoft.azure.management.network.implementation.SecurityRuleInner>> networkSecurityRules = new ArrayList<>();
            networkSecurityRules.add(nsg.inner().securityRules());
            networkSecurityRules.add(nsg.inner().defaultSecurityRules());
            if (networkSecurityRules != null && !networkSecurityRules.isEmpty()) networkSecurityGroupDto.setFirewallRules(getNSGRules(networkSecurityRules));

            networkSecurityGroupDto.setAssociatedAssets(getAssociatedAssets(nsg));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkSecurityGroupDto;
    }

    private List<AssociatedAsset> getAssociatedAssets(com.microsoft.azure.management.network.NetworkSecurityGroup nsg) {

        List<AssociatedAsset> associatedAssetList = new ArrayList<>();

        for (com.microsoft.azure.management.network.Subnet subnet : nsg.listAssociatedSubnets()) {

            AssociatedAsset associatedAsset = new AssociatedAsset();
            associatedAsset.setName(subnet.name());
            associatedAsset.setAddress(subnet.addressPrefix());
            associatedAsset.setAssociatedEntityType("Subnet");
            associatedAssetList.add(associatedAsset);
        }

        if (nsg.networkInterfaceIds() != null && !nsg.networkInterfaceIds().isEmpty()) {
            nsg.networkInterfaceIds().forEach(nic -> {
                AssociatedAsset associatedAsset = new AssociatedAsset();
                associatedAsset.setAssociatedEntityType("Virtual Machine");
                associatedAssetList.add(associatedAsset);
            });
        }

        return associatedAssetList;
    }

    private List<FirewallRule> getNSGRules(List<List<com.microsoft.azure.management.network.implementation.SecurityRuleInner>> networkSecurityRules) {

        List<FirewallRule> firewallRuleList = new ArrayList<>();

        networkSecurityRules.forEach(x -> {
            x.forEach(y -> {
                FirewallRule nsgSecurityRules = new FirewallRule();
                nsgSecurityRules.setDirection(y.direction().toString());
                nsgSecurityRules.setName(y.name());
                nsgSecurityRules.setPriority(y.priority());
                nsgSecurityRules.setDescription(y.description());

                if (y.destinationPortRange() != null) {
                    nsgSecurityRules
                            .setPort(y.destinationPortRange().equalsIgnoreCase("*") ? "Any" : y.destinationPortRange());
                } else {
                    nsgSecurityRules.setPort("");
                }
                if (y.protocol().toString() != null) {
                    nsgSecurityRules.setProtocol(
                            y.protocol().toString().equalsIgnoreCase("*") ? "Any" : y.protocol().toString());
                } else {
                    nsgSecurityRules.setProtocol("");
                }
                if (y.sourceAddressPrefix() != null) {
                    nsgSecurityRules
                            .setSource(y.sourceAddressPrefix().equalsIgnoreCase("*") ? "Any" : y.sourceAddressPrefix());
                } else {
                    nsgSecurityRules.setSource("");
                }
                if (y.destinationAddressPrefix() != null) {
                    nsgSecurityRules.setDestination(
                            y.destinationAddressPrefix().equalsIgnoreCase("*") ? "Any" : y.destinationAddressPrefix());
                } else {
                    nsgSecurityRules.setDestination("");
                }
                nsgSecurityRules.setAction(y.access().toString());

                firewallRuleList.add(nsgSecurityRules);
            });

        });

        return firewallRuleList;
    }
}
