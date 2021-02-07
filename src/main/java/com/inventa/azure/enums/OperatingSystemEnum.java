package com.inventa.azure.enums;

public enum OperatingSystemEnum {

    LINUX("LINUX"),
    WINDOWS("WINDOWS");

    private final String value;

    OperatingSystemEnum(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }

}

