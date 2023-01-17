package com.example.ds2022_30241_fariseu_teodora.service;

import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ReadConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.notification.NotificationDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.notification.UserNotificationDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.MonitoringDevice;
import com.example.ds2022_30241_fariseu_teodora.entity.Notification;
import com.example.ds2022_30241_fariseu_teodora.repository.DeviceRepo;
import com.example.ds2022_30241_fariseu_teodora.repository.NotificationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    protected NotificationRepo notificationRepo;

    @Autowired
    protected DeviceRepo deviceRepo;

    public void saveNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        MonitoringDevice device = new MonitoringDevice();
        device.setId(notificationDTO.getDeviceID());
        notification.setDevice(device);
        notification.setMessage(notificationDTO.getMessage());
        notificationRepo.save(notification);
    }

    public boolean shouldSendNotification(ReadConsumptionDTO readConsumption) {
        boolean res = !notificationRepo.existsNotificationForDevice(readConsumption.getDeviceId(),
                readConsumption.timestampLocalDateTime().plusMinutes(-1),
                readConsumption.timestampLocalDateTime()
        );
        log.info("Should send: "+res);
        return res;
    }

    public String getRecipient(String deviceId) {
        return deviceRepo.findById(deviceId).get().getOwner().getId();
    }

    public List<UserNotificationDTO> getAllUserNotifications(String userId) {
        return notificationRepo.findAllByDeviceOwner(userId).stream()
                .map(n -> {
                    UserNotificationDTO notif = new UserNotificationDTO();
                    notif.setTimestamp(n.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));
                    notif.setDeviceId(n.getDevice().getId());
                    notif.setMessage(n.getMessage());
                    notif.setLocation(n.getDevice().getAddress());
                    return notif;
                }).collect(Collectors.toList());
    }
}
