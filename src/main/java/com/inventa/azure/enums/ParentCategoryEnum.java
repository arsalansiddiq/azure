package com.inventa.azure.enums;

public enum ParentCategoryEnum {

    COMPUTE("Compute"),
    HYPERVISOR("Hypervisor"),
    FIREWALL("Firewall"),
    DATABASE_INSTANCE("Database Instance"),
    LOAD_BALANCER("Load Balancer"),
    SERVERLESS_FUNCTION("Serverless Function"),
    CONTAINER("CONTAINER"),
    CLUSTER("Cluster"),
    STORAGE("Storage"),
    DNS_ZONE("DNS Zone"),
    INTERNET_GATEWAY("Internet Gateway"),
    NAT_GATEWAY("NAT Gateway"),
    TRAFFIC_MANAGER("Traffic Manager"),
    NETWORK("Network"),
    APPLICATION_GATEWAY("Application Gateway");

    private final String value;

    ParentCategoryEnum(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }

}
