package com.data.session10.service.notification;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.Notification;
import com.data.session10.repository.AccountRepository;
import com.data.session10.repository.NotificationRepository;
import com.data.session10.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Notification> getNotificationsByAccountId(UUID accountId) {
        return notificationRepository.findByAccountId(accountId);
    }

    @Override
    public void sendNotification(UUID accountId, String content) {
        Account acc = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Notification noti = Notification.builder()
                .id(UUID.randomUUID())
                .account(acc)
                .content(content)
                .status("chưa đọc")
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepository.save(noti);
    }
}
