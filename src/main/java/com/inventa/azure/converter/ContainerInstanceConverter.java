package com.inventa.azure.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.common.Constants;
import com.inventa.azure.domain.Device;
import com.inventa.azure.dto.ci.ContainerInstanceDto;
import com.inventa.azure.dto.common.CorrelationDto;
import com.inventa.azure.enums.AdapterEnum;
import com.inventa.azure.enums.AdapterPropertyEnum;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.enums.ParentCategoryEnum;
import com.inventa.azure.valueobject.Common;
import com.inventa.azure.valueobject.adapter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import sun.awt.image.ImageWatched;

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
        if (ciDto.getIpAddres()!=null && !ciDto.getIpAddres().isEmpty()) {
            common.setIpAddress(Collections.singleton(ciDto.getIpAddres()));
        }
        common.setSource(Arrays.asList(AZURE));
        common.setType(DeviceTypeEnum.CONTAINER_INSTANCE);
        common.setParentType(ParentCategoryEnum.CONTAINER);
        common.setAssetTags(ciDto.getTags());
        common.setPorts(ciDto.getPorts());
        common.setAdapterProperties(getProperties());

        Map<String, Object> summary = new HashMap<>();
        summary.put("Subscription ID", ciDto.getSubscriptionId());
        summary.put("Subscription Name", ciDto.getSubscriptionName());
        summary.put("Resource Group", ciDto.getResourceGroupName());
        summary.put("Host Name", ciDto.getAssetName());
        summary.put("Status", ciDto.getStatus());
        summary.put("Location", ciDto.getLocation());
        summary.put("OS", ciDto.getOsType());
        summary.put("Restart policy", ciDto.getRestartPolicy());
        summary.put("CPU core", ciDto.getCpuCore());
        summary.put("GPU", ciDto.getGpu());
        summary.put("RAM", ciDto.getRam());
        summary.put("Ip address", ciDto.getIpAddres());
        summary.put("FQDN", ciDto.getFqdn());
        summary.put("DNS name label", ciDto.getDnsNameLabel());
        summary.put("Container count", ciDto.getContianerCount());
        summary.put("Image", ciDto.getImage());
        summary.put("Start time", ciDto.getStartTime());
        summary.put("Previous state", ciDto.getPreviousState());
        summary.put("Restart count", ciDto.getRestartCount());
        common.setSummary(summary);

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
        common.setHostname(ciDto.getAssetName());
        common.setFetchTime(new Date());

        /*OS Mapping*/
        Os os = new Os();
        os.setType(ciDto.getOsType());
        common.setOs(os);
        /*End Mapping*/

        /*CPU Mapping*/
        Cpu cpu = new Cpu();
        cpu.setCoreCount((long) ciDto.getCpuCore());
        common.setCpus(Collections.singletonList(cpu));
        /*End Mapping*/

        /*RAM Mapping*/
        Ram ram = new Ram();
        ram.setTotalRam((long) ciDto.getRam());
        /*End Mapping*/

        /*IP Address Mapping*/
        if (ciDto.getIpAddres()!=null && !ciDto.getIpAddres().isEmpty()) {
            Set<String> ipAddresses = new HashSet<String>(Collections.singleton(ciDto.getIpAddres()));
            common.setIpAddressSet(ipAddresses);
        }
        /*End Mapping*/

        /*Open Ports Mapping*/
        if (ciDto.getPorts()!=null && !ciDto.getPorts().isEmpty()) {
            Set<OpenPorts> openPorts = new HashSet<>();
            ciDto.getPorts().forEach(x -> {
                OpenPorts openPorts1 = new OpenPorts();
                openPorts1.setPort(x.getPort());
                openPorts1.setTransport(x.getProtocol());
                openPorts.add(openPorts1);
            });
            common.setOpenPorts(openPorts);
        }
        /*End Mapping*/

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
        specific.put("Connector Common", connectorCommon);

        LinkedHashMap essentials = new LinkedHashMap();
        essentials.put("Resource Group", ciDto.getResourceGroupName());
        essentials.put("Location", ciDto.getLocation());
        essentials.put("Status", ciDto.getStatus());
        essentials.put("Subscription", ciDto.getSubscriptionName());
        essentials.put("Subscription ID", ciDto.getSubscriptionId());
        essentials.put("OS type", ciDto.getOsType());
        if (ciDto.getIpAddres()!=null && !ciDto.getIpAddres().isEmpty()) {
            essentials.put("IP address ", ciDto.getIpAddres());
        }
        essentials.put("FQDN", ciDto.getFqdn());
        essentials.put("Container count", ciDto.getContianerCount());
        specific.put("Essentials", essentials);

        if (ciDto.getTags()!=null && !ciDto.getTags().isEmpty()) {
            specific.put("Tags", ciDto.getTags());
        }

        LinkedHashMap container = new LinkedHashMap();
        container.put("Name", ciDto.getAssetName());
        container.put("Image", ciDto.getImage());
        container.put("State", ciDto.getStatus());
        container.put("Previous state", ciDto.getPreviousState());
        container.put("Start time", ciDto.getStartTime());
        container.put("Restart count", ciDto.getRestartCount());
        container.put("CPU cores", ciDto.getCpuCore());
        container.put("Memory", ciDto.getRam() + " GiB");
        container.put("GPU SKU", ciDto.getGpuSKU());
        container.put("GPU count", ciDto.getGpu());
        specific.put("Containers", container);

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
