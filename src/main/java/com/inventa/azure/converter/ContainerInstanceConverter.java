package com.inventa.azure.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.common.Constants;
import com.inventa.azure.domain.Device;
import com.inventa.azure.dto.ci.ContainerInstanceDto;
import com.inventa.azure.dto.common.CorrelationDto;
import com.inventa.azure.dto.nsg.NetworkSecurityGroupDto;
import com.inventa.azure.enums.AdapterEnum;
import com.inventa.azure.enums.AdapterPropertyEnum;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.enums.ParentCategoryEnum;
import com.inventa.azure.valueobject.Common;
import com.inventa.azure.valueobject.adapter.AdapterData;
import com.inventa.azure.valueobject.adapter.FirewallRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.inventa.azure.common.Constants.AZURE;

@Component
public class ContainerInstanceConverter implements DeviceConverter{

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Device toDevice(Map deviceMap) {
        DeviceTypeEnum deviceType = DeviceTypeEnum.CONTAINER_INSTANCE;

        Device device = new Device();

        //set type of device
        device.setType(deviceType);
        device.setParentType(ParentCategoryEnum.CONTAINER);

        //set adapter properties
        device.setAdapterProperties(getProperties());

        //set common data
        device.setCommon(toCommon(deviceMap));
        if(device.getCommon()!=null){
            device.getCommon().setType(device.getType());
            device.getCommon().setParentType(ParentCategoryEnum.CONTAINER);
            device.getCommon().setAdapterProperties(device.getAdapterProperties());
        }

        //set adapter data
        Map<String, Object> adapterHashMap = new HashMap<>();
        adapterHashMap.put(AZURE, toAdapterData(deviceMap));
        device.setAdapters(adapterHashMap);

        Set<String> instanceSet = new HashSet<>();
        instanceSet.add(toInstanceId(deviceMap));
        device.setInstanceAssociationSet(instanceSet);

        return device;
    }

    @Override
    public Common toCommon(Map map) {

        ContainerInstanceDto ciDto = objectMapper.convertValue(map, ContainerInstanceDto.class);

        Common common = new Common();

        common.setIdentifier(ciDto.getIdentifier());
        common.setAssetName(ciDto.getAssetName());
        common.setHostName(ciDto.getAssetName());
        common.setSource(Arrays.asList(AZURE));
        common.setAssetTags(ciDto.getTags());

        Map<String, Object> summary = new HashMap<>();
        summary.put("Subscription ID", ciDto.getSubscriptionId());
        summary.put("Subscription Name", ciDto.getSubscriptionName());
        summary.put("Resource Group", ciDto.getResourceGroupName());
        summary.put("Host Name", ciDto.getAssetName());
        summary.put("Location", ciDto.getLocation());
        summary.put("OS type", ciDto.getOsType());
        summary.put("RAM", ciDto.getRam());
        common.setSummary(summary);

        common.setPorts(ciDto.getPorts());

        common.setFetchTime(new Date());

        return common;
    }

    @Override
    public String toInstanceId(Map deviceHashMap) {
        return (String) deviceHashMap.get("instanceAssociationId");
    }

    @Override
    public Criteria getCorrelationCriteria(Map map) {
        ContainerInstanceDto ciDto = objectMapper.convertValue(map,ContainerInstanceDto.class);

        CorrelationDto correlationDto = getCorrelationDto(ciDto);

        Criteria identifierCriteria = (correlationDto.getIdentifier()!=null && !correlationDto.getIdentifier().isEmpty()) ?
                getIdentifierCriteria(correlationDto.getIdentifier()) : null;
        Criteria hostNameCriteria = (correlationDto.getHostname()!=null && !correlationDto.getHostname().isEmpty()) ?
                getHostnameCriteria(correlationDto.getHostname()) : null;
        Criteria ipAddressCriteria = (correlationDto.getIpAddresses()!=null && !correlationDto.getIpAddresses().isEmpty()) ?
                getIPAddressCriteria(correlationDto.getIpAddresses()) : null;

        if(identifierCriteria!=null && hostNameCriteria != null && ipAddressCriteria!=null){
            return new Criteria().orOperator(identifierCriteria,hostNameCriteria, ipAddressCriteria);
        }else if (hostNameCriteria != null){
            return hostNameCriteria;
        } else if (ipAddressCriteria != null) {
            return ipAddressCriteria;
        }
        else {
            return identifierCriteria;
        }
    }

    @Override
    public LinkedHashMap toAdapterData(Map deviceHashMap) {
        AdapterData adapterData = new AdapterData();

        ContainerInstanceDto ciDto =  objectMapper.convertValue(deviceHashMap, ContainerInstanceDto.class);

        adapterData.setAssetId(ciDto.getIdentifier());
        adapterData.setCommon(toAdapterCommon(ciDto));
        adapterData.setSpecific(toSpecific(ciDto));
        adapterData.setQuery(deviceHashMap);

        return objectMapper.convertValue(adapterData,LinkedHashMap.class);
    }

    private com.inventa.azure.valueobject.adapter.Common toAdapterCommon(ContainerInstanceDto ciDto) {

        com.inventa.azure.valueobject.adapter.Common common = new com.inventa.azure.valueobject.adapter.Common();
        common.setAdapterDeviceTypeEnum("Container Instance");
        common.setAssetId(ciDto.getIdentifier());
        common.setIpAddressSet(Collections.singleton(ciDto.getIpAddres()));
        common.setSource(AdapterEnum.AZURE);
        common.setName(ciDto.getAssetName());
        common.setFetchTime(new Date());

//        common.setOpenPorts(); "Remember to discuss with Inshal about nre std left tabs 😎"

        Set<AdapterPropertyEnum> adapterPropertyEnums = new HashSet<>();
        adapterPropertyEnums.add(AdapterPropertyEnum.ClOUD_PROVIDER);
        adapterPropertyEnums.add(AdapterPropertyEnum.MANAGER);
        common.setAdapterPropertyEnumSet(adapterPropertyEnums);

        return common;
    }

    private LinkedHashMap toSpecific(ContainerInstanceDto ciDto) {

        LinkedHashMap specific =  new LinkedHashMap();

        LinkedHashMap connectorCommon = new LinkedHashMap();
        connectorCommon.put("SUBSCRIPTION ID", ciDto.getSubscriptionId());
        connectorCommon.put("SUBSCRIPTION NAME", ciDto.getSubscriptionName());
        connectorCommon.put("RESOURCE GROUP NAME", ciDto.getResourceGroupName());

        LinkedHashMap details = new LinkedHashMap();

        LinkedHashMap essentials = new LinkedHashMap();
        essentials.put("Resource Group", ciDto.getResourceGroupName());
        essentials.put("Location", ciDto.getLocation());
        essentials.put("Subscription", ciDto.getSubscriptionName());
        essentials.put("Subscription ID", ciDto.getSubscriptionId());
        essentials.put("Status", ciDto.getSubscriptionId());
        essentials.put("Tags", ciDto.getTags());
        details.put("Essentials", essentials);


        specific.put("Connector Common", connectorCommon);
        specific.put("Details", details);

        return specific;
    }

    private CorrelationDto getCorrelationDto(ContainerInstanceDto ciDto) {
        CorrelationDto correlationDto = new CorrelationDto();
        correlationDto.setHostname(ciDto.getAssetName());
        correlationDto.setIdentifier(ciDto.getIdentifier());
        correlationDto.setIpAddresses(Collections.singleton(ciDto.getIpAddres()));
        return correlationDto;
    }

    private Criteria getHostnameCriteria(String hostname) {
        return Criteria.where(Constants.COMMON_HOSTNAME).is(hostname);
    }

    private Criteria getIdentifierCriteria(String identifier) {
        return Criteria.where(Constants.IDENTIFIER).is(identifier);
    }

    private Criteria getIPAddressCriteria(Set<String> ipAddresses) {
        return Criteria.where(Constants.COMMON_IP_ADDRESS).is(ipAddresses);
    }

    private Set<String> getProperties() {
        Set<String> adapterProperties = new HashSet<>();
        adapterProperties.add("CLOUD PROVIDER");
        adapterProperties.add("MANAGER");
        return adapterProperties;
    }
}
