package com.inventa.azure.enums;

public enum DeviceTypeEnum {


    VIRTUAL_MACHINE("VIRTUAL_MACHINE"),
    NETWORK_SECURITY_GROUP("NETWORK_SECURITY_GROUP"),
    CONTAINER_INSTANCE("CONTAINER_INSTANCE"),
    NETWORK_INTERFACE("NETWORK_INTERFACE");

    private final String value;

    DeviceTypeEnum(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }

}
