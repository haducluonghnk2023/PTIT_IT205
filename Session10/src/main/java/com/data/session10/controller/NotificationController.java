package com.data.session10.controller;

import com.data.session10.model.entity.Notification;
import com.data.session10.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Notification>> getByAccount(@PathVariable UUID accountId) {
        return ResponseEntity.ok(notificationService.getNotificationsByAccountId(accountId));
    }
}
