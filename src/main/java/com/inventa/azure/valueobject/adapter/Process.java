package com.inventa.azure.valueobject.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"processName","processID","listeningON"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Process {

    @JsonProperty("Process Name")
    private String processName;
    @JsonProperty("PID")
    private Long processID;
    @JsonProperty("Listening On")
    private String listeningON;


    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setProcessID(Long processID) {
        this.processID = processID;
    }

    public void setListeningON(String listeningON) {
        this.listeningON = listeningON;
    }
}