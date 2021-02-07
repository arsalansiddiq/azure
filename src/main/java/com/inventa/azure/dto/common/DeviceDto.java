package com.inventa.azure.dto.common;

import com.inventa.azure.common.Constants;
import com.inventa.azure.enums.DeviceTypeEnum;

public class DeviceDto {

    private DeviceTypeEnum type;
    private String source = Constants.AZURE;
    private String instanceAssociationId;

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public String getInstanceAssociationId() {
        return instanceAssociationId;
    }

    public void setInstanceAssociationId(String instanceAssociationId) {
        this.instanceAssociationId = instanceAssociationId;
    }
}
