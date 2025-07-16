package com.data.session07.service.statistic;

import com.data.session07.repository.HarvestRepository;
import com.data.session07.repository.PaymentSlipRepository;
import com.data.session07.repository.SeedRepository;
import com.data.session07.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    @Override
    public int countRemainingSeeds() {
        return seedRepository.sumAllQuantity();
    }

    @Override
    public double totalHarvestMoneyThisMonth() {
        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end = YearMonth.now().atEndOfMonth();
        return harvestRepository.sumTotalPriceByDateRange(start, end);
    }

    @Override
    public double totalPaymentSlipsThisMonth() {
        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end = YearMonth.now().atEndOfMonth();
        return paymentSlipRepository.sumMoneyByCreatedAtBetween(start.atStartOfDay(), end.atTime(23, 59));
    }

    @Override
    public Map<String, Double> profitLossOverYear() {
        Map<String, Double> result = new HashMap<>();
        int year = LocalDate.now().getYear();
        for (int i = 1; i <= 12; i++) {
            LocalDate start = LocalDate.of(year, i, 1);
            LocalDate end = YearMonth.of(year, i).atEndOfMonth();

            double income = harvestRepository.sumTotalPriceByDateRange(start, end);
            double expense = paymentSlipRepository.sumMoneyByCreatedAtBetween(start.atStartOfDay(), end.atTime(23, 59));

            result.put(String.format("%02d/%d", i, year), income - expense);
        }
        return result;
    }

    @Override
    public double totalWorkerSalaryThisMonth() {
        return 0;
    }
}
