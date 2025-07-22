package com.data.session10.service.transaction;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.Transaction;
import com.data.session10.repository.AccountRepository;
import com.data.session10.repository.TransactionRepository;
import com.data.session10.service.NotificationService;
import com.data.session10.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private  AccountRepository accountRepository;
    @Autowired
    private  TransactionRepository transactionRepository;
    @Autowired
    private  NotificationService notificationService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    public Transaction transferMoney(Transaction transaction) {
        Account sender = accountRepository.findById(transaction.getSender().getId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        Account receiver = accountRepository.findById(transaction.getReceiver().getId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        try {
            if (sender.getMoney().doubleValue() < transaction.getMoney()) {
                transaction.setId(UUID.randomUUID());
                transaction.setStatus("thất bại");
                transaction.setCreatedAt(LocalDateTime.now());
                transactionRepository.save(transaction);
                logger.error("Giao dịch thất bại: số dư không đủ");
                return transaction;
            }

            // Trừ tiền sender
            sender.setMoney(sender.getMoney().subtract(java.math.BigDecimal.valueOf(transaction.getMoney())));
            accountRepository.save(sender);

            // Cộng tiền receiver
            receiver.setMoney(receiver.getMoney().add(java.math.BigDecimal.valueOf(transaction.getMoney())));
            accountRepository.save(receiver);

            transaction.setId(UUID.randomUUID());
            transaction.setStatus("thành công");
            transaction.setCreatedAt(LocalDateTime.now());
            transactionRepository.save(transaction);

            // Gửi thông báo
            notificationService.sendNotification(sender.getId(),
                    "Bạn đã chuyển " + transaction.getMoney() + " đến " + receiver.getFullName()
                            + ". Số dư hiện tại: " + sender.getMoney());

            notificationService.sendNotification(receiver.getId(),
                    "Bạn đã nhận " + transaction.getMoney() + " từ " + sender.getFullName()
                            + ". Số dư hiện tại: " + receiver.getMoney());

            return transaction;

        } catch (Exception e) {
            logger.error("Giao dịch lỗi: {}", e.getMessage());
            transaction.setId(UUID.randomUUID());
            transaction.setStatus("thất bại");
            transaction.setCreatedAt(LocalDateTime.now());
            transactionRepository.save(transaction);
            return transaction;
        }
    }
}
