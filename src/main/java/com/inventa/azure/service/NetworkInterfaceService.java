package com.inventa.azure.service;

import com.inventa.azure.dto.AzureProperties;

import java.util.List;
import java.util.Map;

public interface NetworkInterfaceService {
    List<Map> getNetworkInterface(String instanceId, AzureProperties azureProperties);
}
