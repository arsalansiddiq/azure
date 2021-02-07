package com.inventa.azure.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.common.Constants;
import com.inventa.azure.domain.Device;
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
public class NetworkSecurityGroupConverter implements DeviceConverter {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Device toDevice(Map deviceMap) {

        DeviceTypeEnum deviceType = DeviceTypeEnum.NETWORK_SECURITY_GROUP;

        Device device = new Device();

        //set type of device
        device.setType(deviceType);
        device.setParentType(ParentCategoryEnum.FIREWALL);

        //set adapter properties
        device.setAdapterProperties(getProperties());

        //set common data
        device.setCommon(toCommon(deviceMap));
        if(device.getCommon()!=null){
            device.getCommon().setType(device.getType());
            device.getCommon().setParentType(ParentCategoryEnum.FIREWALL);
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

        NetworkSecurityGroupDto nsgDto = objectMapper.convertValue(map, NetworkSecurityGroupDto.class);

        Common common = new Common();

        common.setIdentifier(nsgDto.getIdentifier());
        common.setAssetName(nsgDto.getAssetName());
        common.setHostName(nsgDto.getAssetName());
        common.setSource(Arrays.asList(AZURE));
        common.setAssetTags(nsgDto.getTags());

        Map<String, Object> summary = new HashMap<>();
        summary.put("Subscription ID", nsgDto.getSubscriptionId());
        summary.put("Subscription Name", nsgDto.getSubscriptionName());
        summary.put("Resource Group", nsgDto.getResourceGroupName());
        summary.put("Host Name", nsgDto.getAssetName());
        summary.put("Location", nsgDto.getLocation());
        common.setSummary(summary);

        common.setFirewallRules(nsgDto.getFirewallRules());
        common.setAssociations(nsgDto.getAssociatedAssets());

        common.setFetchTime(new Date());

        return common;
    }

    @Override
    public String toInstanceId(Map deviceHashMap) {
        return (String) deviceHashMap.get("instanceAssociationId");
    }

    @Override
    public Criteria getCorrelationCriteria(Map map) {
        NetworkSecurityGroupDto nsgDto = objectMapper.convertValue(map,NetworkSecurityGroupDto.class);

        CorrelationDto correlationDto = getCorrelationDto(nsgDto);

        Criteria identifierCriteria = (correlationDto.getIdentifier()!=null && !correlationDto.getIdentifier().isEmpty()) ?
                getIdentifierCriteria(correlationDto.getIdentifier()) : null;
        Criteria hostNameCriteria = (correlationDto.getHostname()!=null && !correlationDto.getHostname().isEmpty()) ?
                getHostnameCriteria(correlationDto.getHostname()) : null;

        if(identifierCriteria!=null && hostNameCriteria != null ){
            return new Criteria().orOperator(identifierCriteria,hostNameCriteria);
        }else if (hostNameCriteria != null){
            return hostNameCriteria;
        }
        else {
            return identifierCriteria;
        }
    }

    private Criteria getHostnameCriteria(String hostname) {
        return Criteria.where(Constants.COMMON_HOSTNAME).is(hostname);
    }

    private Criteria getIdentifierCriteria(String identifier) {
        return Criteria.where(Constants.IDENTIFIER).is(identifier);
    }

    @Override
    public LinkedHashMap toAdapterData(Map deviceHashMap) {
        AdapterData adapterData = new AdapterData();

        NetworkSecurityGroupDto networkSecurityGroupDto =  objectMapper.convertValue(deviceHashMap, NetworkSecurityGroupDto.class);

        adapterData.setAssetId(networkSecurityGroupDto.getIdentifier());
        adapterData.setCommon(toAdapterCommon(networkSecurityGroupDto));
        adapterData.setSpecific(toSpecific(networkSecurityGroupDto));
        adapterData.setQuery(deviceHashMap);

        return objectMapper.convertValue(adapterData,LinkedHashMap.class);
    }

    private CorrelationDto getCorrelationDto(NetworkSecurityGroupDto nsgDto) {
        CorrelationDto correlationDto = new CorrelationDto();
        correlationDto.setHostname(nsgDto.getAssetName());
        correlationDto.setIdentifier(nsgDto.getIdentifier());
        return correlationDto;
    }

    private com.inventa.azure.valueobject.adapter.Common toAdapterCommon(NetworkSecurityGroupDto nsgDto) {

        com.inventa.azure.valueobject.adapter.Common common = new com.inventa.azure.valueobject.adapter.Common();
        common.setAdapterDeviceTypeEnum("Network Security Group");
        common.setAssetId(nsgDto.getIdentifier());
        common.setSource(AdapterEnum.AZURE);
        common.setName(nsgDto.getAssetName());
        common.setFetchTime(new Date());

        if (nsgDto.getFirewallRules()!=null && !nsgDto.getFirewallRules().isEmpty()) {
            List<FirewallRule> firewallRules = new ArrayList<>();
            nsgDto.getFirewallRules().forEach(x -> {
                try {
                    FirewallRule firewallRule = new FirewallRule();
                    if (x.getDirection().equalsIgnoreCase("Inbound")) {
                        firewallRule.setSource(x.getSource());
                        firewallRule.setSourcePort(x.getPort());
                        firewallRule.setDestination(x.getDestination());
                        firewallRule.setProtocol(x.getProtocol());
                    } else {
                        firewallRule.setSource(x.getSource());
                        firewallRule.setDestination(x.getDestination());
                        firewallRule.setDestinationPort(x.getPort());
                        firewallRule.setProtocol(x.getProtocol());
                    }
                    firewallRules.add(firewallRule);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            common.setFirewallRules(firewallRules);
        }

        Set<AdapterPropertyEnum> adapterPropertyEnums = new HashSet<>();
        adapterPropertyEnums.add(AdapterPropertyEnum.ClOUD_PROVIDER);
        adapterPropertyEnums.add(AdapterPropertyEnum.MANAGER);
        common.setAdapterPropertyEnumSet(adapterPropertyEnums);

        return common;
    }

    private LinkedHashMap toSpecific(NetworkSecurityGroupDto nsgDto) {

        LinkedHashMap specific =  new LinkedHashMap();

        LinkedHashMap connectorCommon = new LinkedHashMap();
        connectorCommon.put("SUBSCRIPTION ID", nsgDto.getSubscriptionId());
        connectorCommon.put("SUBSCRIPTION NAME", nsgDto.getSubscriptionName());
        connectorCommon.put("RESOURCE GROUP NAME", nsgDto.getResourceGroupName());

        LinkedHashMap details = new LinkedHashMap();

        LinkedHashMap essentials = new LinkedHashMap();
        essentials.put("Resource Group", nsgDto.getResourceGroupName());
        essentials.put("Location", nsgDto.getLocation());
        essentials.put("Subscription", nsgDto.getSubscriptionName());
        essentials.put("Subscription ID", nsgDto.getSubscriptionId());
        essentials.put("Tags", nsgDto.getTags());
        details.put("Essentials", essentials);

        if(nsgDto.getFirewallRules()!=null && !nsgDto.getFirewallRules().isEmpty()){
            try {
                List<Map> inboundFirewallRules = new ArrayList<>();
                List<Map> outboundFirewallRules = new ArrayList<>();
                for (com.inventa.azure.dto.nsg.FirewallRule rule : nsgDto.getFirewallRules()) {
                    if (rule.getDirection().equalsIgnoreCase("Inbound")) {
                        Map nsgRule = new LinkedHashMap();
                        nsgRule.put("Priority", rule.getPriority());
                        nsgRule.put("Name", rule.getName());
                        nsgRule.put("Port", rule.getPort());
                        nsgRule.put("Protocol", rule.getProtocol());
                        nsgRule.put("Source", rule.getSource());
                        nsgRule.put("Destination", rule.getDestination());
                        nsgRule.put("Action", rule.getAction());
                        nsgRule.put("Description", rule.getDescription());
                        inboundFirewallRules.add(nsgRule);
                    }
                    if (rule.getDirection().equalsIgnoreCase("Outbound")) {
                        Map nsgRule = new LinkedHashMap();
                        nsgRule.put("Priority", rule.getPriority());
                        nsgRule.put("Name", rule.getName());
                        nsgRule.put("Port", rule.getPort());
                        nsgRule.put("Protocol", rule.getProtocol());
                        nsgRule.put("Source", rule.getSource());
                        nsgRule.put("Destination", rule.getDestination());
                        nsgRule.put("Action", rule.getAction());
                        nsgRule.put("Description", rule.getDescription());
                        outboundFirewallRules.add(nsgRule);
                    }
                }
                details.put("Inbound security rules", inboundFirewallRules);
                details.put("Outbound security rules", outboundFirewallRules);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        specific.put("Connector Common", connectorCommon);
        specific.put("Details", details);

        return specific;
    }

    private Set<String> getProperties() {
        Set<String> adapterProperties = new HashSet<>();
        adapterProperties.add("CLOUD PROVIDER");
        adapterProperties.add("MANAGER");
        return adapterProperties;
    }
}
