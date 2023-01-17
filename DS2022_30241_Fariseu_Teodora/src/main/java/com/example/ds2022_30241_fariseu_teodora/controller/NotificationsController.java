package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.dto.notification.UserNotificationDTO;
import com.example.ds2022_30241_fariseu_teodora.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
@RequestMapping(value = "/notifications")
@RestController
@CrossOrigin
public class NotificationsController {
    @Autowired
    protected NotificationService service;
    @GetMapping("/{userId}")
    protected ResponseEntity<List<UserNotificationDTO>> allNotifications(@PathParam("userId") String user) {
        return ResponseEntity.ok(service.getAllUserNotifications(user));
    }
}
