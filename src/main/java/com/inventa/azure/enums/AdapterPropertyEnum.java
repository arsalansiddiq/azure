package com.inventa.azure.enums;

public enum AdapterPropertyEnum {

    ClOUD_PROVIDER("ClOUD_PROVIDER"),
    MANAGER("MANAGER");

    private final String value;

    AdapterPropertyEnum(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }


}
