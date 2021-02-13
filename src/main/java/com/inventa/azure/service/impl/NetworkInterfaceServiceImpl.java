package com.inventa.azure.service.impl;

//import com.azure.core.http.rest.PagedIterable;
//import com.azure.resourcemanager.AzureResourceManager;
//import com.azure.resourcemanager.network.fluent.models.NetworkInterfaceIpConfigurationInner;
//import com.azure.resourcemanager.network.models.NetworkInterface;
//import com.azure.resourcemanager.resources.models.Subscription;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.inventa.azure.common.AzureClient;
import com.inventa.azure.common.AzureUtils;
import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.nic.NetworkInterfaceDto;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.service.NetworkInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NetworkInterfaceServiceImpl implements NetworkInterfaceService {

    @Autowired
    ObjectMapper objectMapper;

//    @Autowired
//    AzureClient azureClient;

    @Override
    public List<Map> getNetworkInterface(String instanceId, AzureProperties azureProperties) {
        List<Map> networkInterfaces = new ArrayList<>();

        try {

            if(azureProperties!=null) {

//                AzureResourceManager azureResourceManager = azureClient.getAzureClient(azureProperties.getClientId(),
//                        azureProperties.getClientSecret(), azureProperties.getTenantId(), azureProperties.getSubscriptionId());
//
//                PagedIterable<NetworkInterface> nics = azureResourceManager.networkInterfaces().list();
//
//                if (nics != null && nics.iterator().hasNext()) {
//                    nics.forEach(x -> {
//                        try {
//                            networkInterfaces.add(objectMapper.convertValue(formulateNetworkInterface(x, azureResourceManager.getCurrentSubscription(), "Test Tag", instanceId), Map.class));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    });
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkInterfaces;
    }

//    private NetworkInterfaceDto formulateNetworkInterface(NetworkInterface nic, Subscription subscription, String accountTag, String instanceId) {
//
//        NetworkInterfaceDto networkInterfaceDto = new NetworkInterfaceDto();
//
//        try {
//
//            if (instanceId!=null && !instanceId.isEmpty()) networkInterfaceDto.setInstanceAssociationId(instanceId);
//
//            networkInterfaceDto.setAssetName(nic.name());
//            networkInterfaceDto.setIdentifier(nic.id());
//            networkInterfaceDto.setType(DeviceTypeEnum.NETWORK_INTERFACE);
//            networkInterfaceDto.setSubscriptionId(subscription.subscriptionId());
//            networkInterfaceDto.setSubscriptionName(subscription.displayName());
//            networkInterfaceDto.setResourceGroupName(nic.resourceGroupName());
//            networkInterfaceDto.setLocation(nic.region() != null ? nic.region().label() : null);
//            networkInterfaceDto.setTags(AzureUtils.getTabTags(nic.tags(), accountTag));
//            networkInterfaceDto.setMacAddress(nic.macAddress());
//            networkInterfaceDto.setIpForwarding(nic.isIPForwardingEnabled());
//            networkInterfaceDto.setAcceleratedNetworking(nic.isAcceleratedNetworkingEnabled());
//
//            Map ipConfigs = getIPConfigs(nic.innerModel().ipConfigurations());
//            networkInterfaceDto.setPrivateIPs((List<String>) ipConfigs.get("privateIPs"));
//            networkInterfaceDto.setIpConfigs((List<String>) ipConfigs.get("ipConfigIds"));
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return networkInterfaceDto;
//    }
//
//    private Map getIPConfigs(List<NetworkInterfaceIpConfigurationInner> ipConfigurations) {
//
//        Map ipConfigs = new HashMap();
//
//        List<String> ipconfigIds = new ArrayList<>();
//        List<String> privateIPs = new ArrayList<>();
//
//        ipConfigurations.forEach(x -> {
//            ipconfigIds.add(x.id());
//            privateIPs.add(x.privateIpAddress());
//        });
//
//        ipConfigs.put("ipConfigIds", ipconfigIds);
//        ipConfigs.put("privateIPs", privateIPs);
//
//        return ipConfigs;
//    }
}
