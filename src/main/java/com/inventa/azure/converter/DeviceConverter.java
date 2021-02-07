package com.inventa.azure.converter;

import com.inventa.azure.domain.Device;
import com.inventa.azure.valueobject.Common;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.LinkedHashMap;
import java.util.Map;

public interface DeviceConverter {

    Device toDevice(Map deviceMap);
    Common toCommon(Map map);
    String toInstanceId(Map deviceHashMap);
    Criteria getCorrelationCriteria(Map map);
    LinkedHashMap toAdapterData(Map deviceHashMap);


}
