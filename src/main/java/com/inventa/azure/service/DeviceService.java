package com.inventa.azure.service;

import com.inventa.azure.converter.DeviceConverter;

import java.util.Collection;
import java.util.Map;

public interface DeviceService {

    void add(Collection<Map> collection, DeviceConverter deviceConverter);

}
