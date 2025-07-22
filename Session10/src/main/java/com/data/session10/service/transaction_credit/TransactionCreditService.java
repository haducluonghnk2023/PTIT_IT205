package com.data.session10.service.transaction_credit;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.CreditCard;
import com.data.session10.model.entity.TransactionCredit;
import com.data.session10.repository.*;
import com.data.session10.service.NotificationService;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionCreditService {
    @Autowired
    private TransactionCreditRepository transactionCreditRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NotificationService notificationService;

    private final Logger logger = LoggerFactory.getLogger(TransactionCreditService.class);
    @Autowired
    private JavaMailSenderImpl mailSender;

    public TransactionCredit createTransaction(TransactionCredit transaction) {
        CreditCard senderCard = creditCardRepository.findById(transaction.getCreditCardSender().getId())
                .orElseThrow(() -> new RuntimeException("CreditCard not found"));

        BigDecimal totalSpent = senderCard.getAmountSpent().add(transaction.getMoney());

        if (totalSpent.compareTo(senderCard.getSpendingLimit()) > 0) {
            logger.error("Giao dịch thất bại: Vượt quá hạn mức cho phép");
            transaction.setStatus("thất bại");
            return transactionCreditRepository.save(transaction);
        }

        senderCard.setAmountSpent(totalSpent);
        creditCardRepository.save(senderCard);

        transaction.setId(UUID.randomUUID());
        transaction.setStatus("thành công");
        TransactionCredit saved = transactionCreditRepository.save(transaction);

        // Gửi thông báo
        Account senderAcc = senderCard.getAccount();
        Account receiverAcc = transaction.getAccountReceiver();

        notificationService.sendNotification(senderAcc.getId(),
                "Đã quẹt thẻ trừ " + transaction.getMoney() + " VND. Tổng đã chi: " + totalSpent);
        notificationService.sendNotification(receiverAcc.getId(),
                "Bạn đã nhận được " + transaction.getMoney() + " VND từ thẻ tín dụng.");

        return saved;
    }

    @Scheduled(cron = "0 0 12 20 * ?")
    public void sendMonthlySpendingReport() {
        logger.info("Bắt đầu thống kê chi tiêu thẻ tín dụng...");

        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();

        // Lấy tất cả các giao dịch thành công trong tháng hiện tại
        List<TransactionCredit> transactions = transactionCreditRepository
                .findByStatusAndCreatedAtBetween("thành công", start.atStartOfDay(), end.atTime(23, 59, 59));

        // Gom theo chủ thẻ
        Map<UUID, List<TransactionCredit>> groupedByCard = transactions.stream()
                .collect(Collectors.groupingBy(t -> t.getCreditCardSender().getId()));

        for (Map.Entry<UUID, List<TransactionCredit>> entry : groupedByCard.entrySet()) {
            List<TransactionCredit> userTx = entry.getValue();
            CreditCard card = userTx.get(0).getCreditCardSender();
            Account acc = card.getAccount();

            BigDecimal total = userTx.stream()
                    .map(TransactionCredit::getMoney)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Gửi notification tổng
            String notiMessage = "Tổng chi tiêu thẻ tín dụng trong tháng " + currentMonth.getMonthValue() + ": " + total + " VND.";
            notificationService.sendNotification(acc.getId(), notiMessage);

            // Gửi mail chi tiết
            try {
                sendSpendingEmail(acc.getEmail(), acc.getFullName(), currentMonth, total, userTx);
            } catch (Exception e) {
                logger.error("Lỗi khi gửi mail: " + e.getMessage());
            }
        }

        logger.info("Hoàn tất thống kê chi tiêu.");
    }

    private void sendSpendingEmail(String to, String name, YearMonth month, BigDecimal total, List<TransactionCredit> transactions) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Báo cáo chi tiêu thẻ tín dụng - Tháng " + month.getMonthValue());

        StringBuilder html = new StringBuilder();
        html.append("<h3>Xin chào ").append(name).append(",</h3>");
        html.append("<p>Dưới đây là báo cáo chi tiêu thẻ tín dụng trong tháng ").append(month.getMonthValue()).append(":</p>");
        html.append("<p><b>Tổng chi tiêu:</b> ").append(total).append(" VND</p>");
        html.append("<table border='1' cellspacing='0' cellpadding='5'>")
                .append("<tr><th>Ngày</th><th>Số tiền</th><th>Ghi chú</th></tr>");

        for (TransactionCredit t : transactions) {
            html.append("<tr>")
                    .append("<td>").append(t.getCreatedAt().toLocalDate()).append("</td>")
                    .append("<td>").append(t.getMoney()).append("</td>")
                    .append("<td>").append(t.getNote()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table><p>Trân trọng,<br>Ngân hàng XYZ</p>");
        helper.setText(html.toString(), true);

        mailSender.send(message);
    }
}