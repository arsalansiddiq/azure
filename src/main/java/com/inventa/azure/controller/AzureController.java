package com.inventa.azure.controller;

import com.azure.core.http.rest.PagedIterable;
import com.azure.resourcemanager.AzureResourceManager;
import com.azure.resourcemanager.compute.models.VirtualMachine;
import com.inventa.azure.common.AzureClient;
import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.service.NetworkSecurityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AzureController {

    @Autowired
    NetworkSecurityGroupService networkSecurityGroupService;

    @GetMapping("/azure/vms")
    public PagedIterable<VirtualMachine> getVms(){
        AzureClient azureClient = new AzureClient();
        AzureResourceManager azureResourceManager = azureClient.getAzureClient(
                "a40ab72d-e920-421d-ab66-515a59e11cb7",
                "L@lVOB4G6hoR[E/.K7l9mIn1O34.D_1f",
                "ba164d97-20d4-4ca8-a610-28902882de11",
                "b433aca0-7a45-446f-a16a-598111f218fb");

        return azureResourceManager.virtualMachines().list();
    }

    @GetMapping("/azure/nsgs")
    public List<Map> getNSGs(){
        AzureProperties azureProperties = new AzureProperties();
        azureProperties.setClientId("a40ab72d-e920-421d-ab66-515a59e11cb7");
        azureProperties.setClientSecret("L@lVOB4G6hoR[E/.K7l9mIn1O34.D_1f");
        azureProperties.setTenantId("ba164d97-20d4-4ca8-a610-28902882de11");
        azureProperties.setSubscriptionId("b433aca0-7a45-446f-a16a-598111f218fb");

//        AzureResourceManager azureResourceManager = azureClient.getAzureClient(
//                "a40ab72d-e920-421d-ab66-515a59e11cb7",
//                "L@lVOB4G6hoR[E/.K7l9mIn1O34.D_1f",
//                "ba164d97-20d4-4ca8-a610-28902882de11",
//                "b433aca0-7a45-446f-a16a-598111f218fb");

        return networkSecurityGroupService.getNetworkSecurityGroups(null, azureProperties);
    }

}
