package com.inventa.azure.service;

import com.inventa.azure.dto.AzureProperties;

import java.util.List;
import java.util.Map;

public interface NetworkSecurityGroupService {

    List<Map> getNetworkSecurityGroups(String instanceId, AzureProperties azureProperties);
//    List<VirtualMachineSummaryDto> getSummaries(List<VirtualMachine> virtualMachines);

}
