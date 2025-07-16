package com.data.session07.service;

import java.util.Map;

public interface StatisticalService {
    int countRemainingSeeds();

    double totalHarvestMoneyThisMonth();

    double totalPaymentSlipsThisMonth();

    Map<String, Double> profitLossOverYear();

    double totalWorkerSalaryThisMonth();
}
