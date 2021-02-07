package com.inventa.azure.service.impl;

import com.azure.core.http.rest.PagedIterable;
import com.azure.resourcemanager.AzureResourceManager;
import com.azure.resourcemanager.network.fluent.models.SecurityRuleInner;
import com.azure.resourcemanager.network.models.NetworkSecurityGroup;
import com.azure.resourcemanager.network.models.Subnet;
import com.azure.resourcemanager.resources.models.Subscription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.common.AzureClient;
import com.inventa.azure.common.AzureUtils;
import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.nsg.AssociatedAsset;
import com.inventa.azure.dto.nsg.FirewallRule;
import com.inventa.azure.dto.nsg.NetworkSecurityGroupDto;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.service.NetworkSecurityGroupService;
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

    @Override
    public List<Map> getNetworkSecurityGroups(String instanceId, AzureProperties azureProperties) {

        List<Map> networkSecurityGroups = new ArrayList<>();

        try {

            if(azureProperties!=null) {

                AzureResourceManager azureResourceManager = azureClient.getAzureClient(azureProperties.getClientId(),
                        azureProperties.getClientSecret(), azureProperties.getTenantId(), azureProperties.getSubscriptionId());

                PagedIterable<NetworkSecurityGroup> nsgs = azureResourceManager.networkSecurityGroups().list();

                if (nsgs != null && nsgs.iterator().hasNext()) {
                    nsgs.forEach(x -> {
                        try {
//                            networkSecurityGroups.add(objectMapper.convertValue(formulateNetworkSecurityGroup(x, azureResourceManager.getCurrentSubscription(), azureProperties.getAccountTag(), instanceId), Map.class));
                            networkSecurityGroups.add(objectMapper.convertValue(formulateNetworkSecurityGroup(x, azureResourceManager.getCurrentSubscription(), "Test Tag", instanceId), Map.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

            List<List<SecurityRuleInner>> networkSecurityRules = new ArrayList<>();
            networkSecurityRules.add(nsg.innerModel().securityRules());
            networkSecurityRules.add(nsg.innerModel().defaultSecurityRules());
            if (networkSecurityRules != null && !networkSecurityRules.isEmpty()) networkSecurityGroupDto.setFirewallRules(getNSGRules(networkSecurityRules));

            networkSecurityGroupDto.setAssociatedAssets(getAssociatedAssets(nsg));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkSecurityGroupDto;
    }

    private List<AssociatedAsset> getAssociatedAssets(NetworkSecurityGroup nsg) {

        List<AssociatedAsset> associatedAssetList = new ArrayList<>();

        for (Subnet subnet : nsg.listAssociatedSubnets()) {

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

    private List<FirewallRule> getNSGRules(List<List<SecurityRuleInner>> networkSecurityRules) {

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
