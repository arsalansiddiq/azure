package com.inventa.azure.dto.notification;

public class NotificationDto {

    String adapter;
    String description;

    public NotificationDto(String adapter, String description) {
        this.adapter = adapter;
        this.description = description;
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
