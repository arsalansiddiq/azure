package com.inventa.azure.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.converter.DeviceConverter;
import com.inventa.azure.domain.Device;
import com.inventa.azure.enums.DeviceTypeEnum;
import com.inventa.azure.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.inventa.azure.common.Constants.ADAPTER_AZURE;


@Service
public class DeviceServiceImpl {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ObjectMapper objectMapper;

    public void add(Collection<Map> collection, DeviceConverter deviceConverter) {

        List<Device> toAdd = new ArrayList<>();
        List<Map> toUpdate = new ArrayList<>();

        collection.forEach(x-> {

            try {

                if (deviceRepository.existsByCriteria(deviceConverter.getCorrelationCriteria(x))) {
                    toUpdate.add(x);
                } else {
                    Device device = deviceConverter.toDevice(x);
                    toAdd.add(device);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        deviceRepository.insert(toAdd);
        deviceRepository.update(toUpdate,ADAPTER_AZURE, deviceConverter);

    }

}
