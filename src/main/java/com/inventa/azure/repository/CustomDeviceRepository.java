package com.inventa.azure.repository;

import com.inventa.azure.converter.DeviceConverter;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Map;


public interface CustomDeviceRepository {

    boolean existsByCriteria(Criteria criteria);
    void update(List<Map> devices, String key, DeviceConverter converter);

}
