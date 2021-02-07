package com.inventa.azure.proxy;

import com.inventa.azure.dto.notification.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotificationProxy", url = "${notification.service.base.url}")
public interface NotificationProxy {

    @PostMapping(value = "notification/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    void pushInfo(@RequestBody NotificationDto notificationDto);

    @PostMapping(value = "notification/error", produces = {MediaType.APPLICATION_JSON_VALUE})
    void pushError(@RequestBody NotificationDto notificationDto);
}
