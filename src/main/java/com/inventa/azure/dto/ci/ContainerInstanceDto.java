package com.inventa.azure.dto.ci;

import com.inventa.azure.dto.common.AzureDeviceCommon;

import java.util.List;
import java.util.Map;

public class ContainerInstanceDto extends AzureDeviceCommon {

    String image;
    String status;
    String ipAddres;
    String fqdn;
    String dnsNameLabel;
    Integer contianerCount;
    String osType;
    double ram;
    double cpuCore;
    String gpuSKU;
    int gpu;
    String previousState;
    String startTime;
    String restartPolicy;
    int restartCount;
    List<Port> ports;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpAddres() {
        return ipAddres;
    }

    public void setIpAddres(String ipAddres) {
        this.ipAddres = ipAddres;
    }

    public String getDnsNameLabel() {
        return dnsNameLabel;
    }

    public void setDnsNameLabel(String dnsNameLabel) {
        this.dnsNameLabel = dnsNameLabel;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public Integer getContianerCount() {
        return contianerCount;
    }

    public void setContianerCount(Integer contianerCount) {
        this.contianerCount = contianerCount;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public double getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(double cpuCore) {
        this.cpuCore = cpuCore;
    }

    public int getGpu() {
        return gpu;
    }

    public String getGpuSKU() {
        return gpuSKU;
    }

    public void setGpuSKU(String gpuSKU) {
        this.gpuSKU = gpuSKU;
    }

    public void setGpu(int gpu) {
        this.gpu = gpu;
    }

    public String getPreviousState() {
        return previousState;
    }

    public void setPreviousState(String previousState) {
        this.previousState = previousState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getRestartCount() {
        return restartCount;
    }

    public void setRestartCount(int restartCount) {
        this.restartCount = restartCount;
    }

    public String getRestartPolicy() {
        return restartPolicy;
    }

    public void setRestartPolicy(String restartPolicy) {
        this.restartPolicy = restartPolicy;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }
}
