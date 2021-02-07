package com.inventa.azure.dto.nsg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirewallRule {

    String direction;
    String name;
    String description;
    Integer priority;
    String port;
    String protocol;
    String source;
    String destination;
    String action;

    @JsonProperty("Direction")
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Priority")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("Port")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @JsonProperty("Protocol")
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @JsonProperty("Source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("Destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonProperty("Action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
