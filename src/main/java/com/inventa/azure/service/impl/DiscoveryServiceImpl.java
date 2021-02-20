package com.inventa.azure.service.impl;

import com.inventa.azure.converter.NetworkSecurityGroupConverter;
import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.InstanceConfigurationDto;
import com.inventa.azure.dto.notification.NotificationDto;
import com.inventa.azure.proxy.NotificationProxy;
import com.inventa.azure.service.ContainerInstanceService;
import com.inventa.azure.service.DiscoveryService;
import com.inventa.azure.service.NetworkSecurityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.inventa.azure.common.Constants.*;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {

    @Autowired
    DeviceServiceImpl deviceService;

    @Autowired
    NotificationProxy notificationProxy;

    @Autowired
    NetworkSecurityGroupService networkSecurityGroupService;

    @Autowired
    ContainerInstanceService containerInstanceService;

    @Override
    public void triggerDiscovery(List<InstanceConfigurationDto> instances) {

        if (instances != null && !instances.isEmpty()) {
            notificationProxy.pushInfo(
                    new NotificationDto(AZURE_ADAPTER, DISCOVERY_START_DESCRIPTION));

            for (InstanceConfigurationDto instance : instances) {

                if (instance != null && instance.getProperties() != null) {
                    if (instance.getProperties().isNetworkSecurityGroups()) { networkSecurityGroupService.discoverNetworkSecurityGroups(instance.get_id(), instance.getProperties()); }
                    if (instance.getProperties().isNetworkSecurityGroups()) { containerInstanceService.discoverContainerInstances(instance.get_id(), instance.getProperties()); }
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
