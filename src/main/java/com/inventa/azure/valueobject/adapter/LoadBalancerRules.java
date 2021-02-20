package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"listenerIp","listenerPort","listenerProtocol","pool","poolMembers"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoadBalancerRules {

    @JsonProperty("Listener Ip")
    private String listenerIp;
    @JsonProperty("Listener Port")
    private Integer listenerPort;
    @JsonProperty("Protocol")
    private String listenerProtocol;
    @JsonProperty("Pool")
    private List<String> pool;
    @JsonProperty("Pool Members")
    private List<String> poolMembers;

    public String getListenerIp() {
        return listenerIp;
    }

    public void setListenerIp(String listenerIp) {
        this.listenerIp = listenerIp;
    }

    public Integer getListenerPort() {
        return listenerPort;
    }

    public void setListenerPort(Integer listenerPort) {
        this.listenerPort = listenerPort;
    }

    public String getListenerProtocol() {
        return listenerProtocol;
    }

    public void setListenerProtocol(String listenerProtocol) {
        this.listenerProtocol = listenerProtocol;
    }

    public List<String> getPool() {
        return pool;
    }

    public void setPool(List<String> pool) {
        this.pool = pool;
    }

    public List<String> getPoolMembers() {
        return poolMembers;
    }

    public void setPoolMembers(List<String> poolMembers) {
        this.poolMembers = poolMembers;
    }
}