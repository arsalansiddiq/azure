package com.inventa.azure.enums;

public enum AdapterEnum {

    AZURE("adapter_AZURE");

    private final String value;

    AdapterEnum(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }

}
