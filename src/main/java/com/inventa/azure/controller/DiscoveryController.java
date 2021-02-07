package com.inventa.azure.controller;

import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.InstanceConfigurationDto;
import com.inventa.azure.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@RestController
public class DiscoveryController {

    @Autowired
    DiscoveryService discoveryService;

    @PostMapping("/azure/discover")
    public void triggerDiscovery(@RequestBody List<InstanceConfigurationDto> instances) {
        Executors.newSingleThreadExecutor().execute(()-> discoveryService.triggerDiscovery(instances));
        return ;
    }

    @GetMapping("/vmware-esxi/discover/sample")
    public void triggerDiscoverySample() {
        List<InstanceConfigurationDto> instanceConfigurationDtos = new ArrayList<>();
        InstanceConfigurationDto instanceConfigurationDto = new InstanceConfigurationDto();
//        VMWareEsxiProperties vmWareEsxiProperties = new VMWareEsxiProperties();
//        vmWareEsxiProperties.setHostname("192.168.1.181");
//        vmWareEsxiProperties.setUsername("vinventa@vsphere.local");
//        vmWareEsxiProperties.setPassword("Invent@01");
//        instanceConfigurationDto.setProperties(vmWareEsxiProperties);
//        instanceConfigurationDtos.add(instanceConfigurationDto);
        Executors.newSingleThreadExecutor().execute(()-> discoveryService.triggerDiscovery(instanceConfigurationDtos));
        return ;
    }

    @PostMapping("/vmware-esxi/connect")
    public boolean testConnectivity(@RequestBody AzureProperties azureProperties) {
        return discoveryService.testConnectivity(azureProperties);
    }


}
