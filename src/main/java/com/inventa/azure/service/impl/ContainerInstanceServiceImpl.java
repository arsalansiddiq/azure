package com.inventa.azure.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.common.AzureClient;
import com.inventa.azure.common.AzureUtils;
import com.inventa.azure.dto.AzureProperties;
import com.inventa.azure.dto.ci.ContainerInstanceDto;
import com.inventa.azure.dto.ci.Port;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.service.ContainerInstanceService;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.containerinstance.ContainerGroup;
import com.microsoft.azure.management.containerinstance.ContainerPort;
import com.microsoft.azure.management.resources.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContainerInstanceServiceImpl implements ContainerInstanceService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AzureClient azureClient;

    @Autowired
    DeviceServiceImpl deviceService;

    @Override
    public List<Map> getContainerInstances(String instanceId, AzureProperties azureProperties) {

        List<Map> containerInstances = new ArrayList<>();

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

                        containerInstances.addAll(fetchContainerInstances(azure, "Text, test", instanceId));
                    });
                } else {
                    Azure azure = Azure.authenticate(azureClient.getAzureClient(
                            azureProperties.getClientId(), azureProperties.getClientSecret(), azureProperties.getTenantId())).withSubscription(azureProperties.getSubscriptionId());
                    containerInstances.addAll(fetchContainerInstances(azure, "Text, test", instanceId));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return containerInstances;
    }

    private List<Map> fetchContainerInstances(Azure azure, String accountTag, String instanceId) {
        List<Map> containerInstances = new ArrayList<>();

        PagedList<ContainerGroup> cgs = azure.containerGroups().list();

        if (cgs != null && !cgs.isEmpty()) {
            cgs.forEach(x -> {
                try {
                    containerInstances.add(objectMapper.convertValue(formulateContainerInstance(x, azure.getCurrentSubscription(),
                            accountTag, instanceId), Map.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

//        deviceService.add(containerInstances, null);
        return containerInstances;
    }

    private ContainerInstanceDto formulateContainerInstance(ContainerGroup ci, Subscription subscription, String accountTag, String instanceId) {

        ContainerInstanceDto containerInstanceDto = new ContainerInstanceDto();

        try {

            if (instanceId!=null && !instanceId.isEmpty()) containerInstanceDto.setInstanceAssociationId(instanceId);

            containerInstanceDto.setAssetName(ci.name());
            containerInstanceDto.setIdentifier(ci.id());
            containerInstanceDto.setType(DeviceTypeEnum.CONTAINER);
            containerInstanceDto.setSubscriptionId(subscription.subscriptionId());
            containerInstanceDto.setSubscriptionName(subscription.displayName());
            containerInstanceDto.setResourceGroupName(ci.resourceGroupName());
            containerInstanceDto.setLocation(ci.region() != null ? ci.region().label() : null);
            containerInstanceDto.setTags(AzureUtils.getTabTags(ci.tags(), accountTag));
            containerInstanceDto.setRestartPolicy(String.valueOf(ci.restartPolicy()));
            containerInstanceDto.setOsType(String.valueOf(ci.osType()));
            containerInstanceDto.setIpAddres(ci.ipAddress());
            containerInstanceDto.setFqdn(ci.fqdn());
            containerInstanceDto.setDnsNameLabel(ci.dnsPrefix());
            containerInstanceDto.setStatus(ci.state());

            if (ci.containers() != null && !ci.containers().isEmpty()) {
                containerInstanceDto.setContianerCount(ci.inner().containers().size());
                containerInstanceDto.setImage(ci.inner().containers().get(0).image());
                containerInstanceDto.setRam(ci.inner().containers().get(0).resources().requests().memoryInGB());
                containerInstanceDto.setCpuCore(ci.inner().containers().get(0).resources().requests().cpu());
                containerInstanceDto.setGpu(ci.inner().containers().get(0).resources().requests().gpu()
                        != null ? ci.inner().containers().get(0).resources().requests().gpu().count() : 0);
                containerInstanceDto.setRestartCount(ci.inner().containers().get(0).instanceView().restartCount());
                containerInstanceDto.setPreviousState(ci.inner().containers().get(0).instanceView().previousState()
                        != null ? ci.inner().containers().get(0).instanceView().previousState().state() : null);
                containerInstanceDto.setStartTime(ci.inner().containers().get(0).instanceView().currentState().startTime()
                        != null ? AzureUtils.toStringDateTime(ci.inner().containers().get(0).instanceView().currentState().startTime()) : null);
                containerInstanceDto.setPorts(getPorts(ci.inner().containers().get(0).ports()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return containerInstanceDto;
    }

    private List<Port> getPorts(List<ContainerPort> ports) {

        List<Port> portList = new ArrayList<>();

        ports.forEach(x -> {
            Port port = new Port();
            port.setProtocol(x.protocol().toString());
            port.setPort(x.port());
            portList.add(port);
        });

        return portList;
    }
}
