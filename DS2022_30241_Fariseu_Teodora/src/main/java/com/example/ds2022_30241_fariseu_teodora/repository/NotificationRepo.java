package com.example.ds2022_30241_fariseu_teodora.repository;

import com.example.ds2022_30241_fariseu_teodora.entity.Notification;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, String> {
    @Query(
            value = "SELECT EXISTS(SELECT * FROM notification WHERE \n" +
                    "notification.device_id = ?1 " +
                    "AND notification.timestamp >= ?2 AND notification.timestamp < ?3 );",
            nativeQuery = true
    )
    Boolean existsNotificationForDevice(String deviceID, LocalDateTime start, LocalDateTime end);

    @Query(
            value = "SELECT * FROM notification INNER JOIN monitoring_device " +
                    "ON notification.device_id = monitoring_device.device_id " +
                    "WHERE monitoring_device.user_id = ?1 ORDER BY notification.timestamp DESC;",
            nativeQuery = true
    )
    List<Notification> findAllByDeviceOwner(String owner);
}
