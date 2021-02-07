package com.inventa.azure.service;

import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.InstanceConfigurationDto;

import java.util.List;

public interface DiscoveryService {

    void triggerDiscovery(List<InstanceConfigurationDto> instances);
    boolean testConnectivity(AzureProperties azureProperties);

}
