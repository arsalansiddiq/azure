package com.inventa.azure.service.impl;

import com.inventa.azure.converter.NetworkSecurityGroupConverter;
import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.InstanceConfigurationDto;
import com.inventa.azure.dto.notification.NotificationDto;
import com.inventa.azure.proxy.NotificationProxy;
import com.inventa.azure.service.DiscoveryService;
import com.inventa.azure.service.NetworkSecurityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.inventa.azure.common.Constants.AZURE_ADAPTER;
import static com.inventa.azure.common.Constants.DISCOVERY_END_DESCRIPTION;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {

    @Autowired
    DeviceServiceImpl deviceService;

    @Autowired
    NotificationProxy notificationProxy;

    @Autowired
    NetworkSecurityGroupService networkSecurityGroupService;

    @Autowired
    NetworkSecurityGroupConverter networkSecurityGroupConverter;

    @Override
    public void triggerDiscovery(List<InstanceConfigurationDto> instances) {

        if (instances != null && !instances.isEmpty()) {
            for (InstanceConfigurationDto instance : instances) {
                if (instance != null && instance.getProperties() != null) {
                    deviceService.add(networkSecurityGroupService.getNetworkSecurityGroups(instance.get_id(), instance.getProperties()), networkSecurityGroupConverter);
                }
            }
            notificationProxy.pushInfo(
                    new NotificationDto(AZURE_ADAPTER, DISCOVERY_END_DESCRIPTION)
            );
        }

    }

    @Override
    public boolean testConnectivity(AzureProperties azureProperties) {
        return false;
    }
}
