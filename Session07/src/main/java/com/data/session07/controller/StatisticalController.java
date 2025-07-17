package com.data.session07.controller;

import com.data.session07.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/remaining-seeds")
    public ResponseEntity<Integer> countRemainingSeeds() {
        return ResponseEntity.ok(statisticalService.countRemainingSeeds());
    }

    @GetMapping("/harvest-money")
    public ResponseEntity<Double> totalHarvestMoney() {
        return ResponseEntity.ok(statisticalService.totalHarvestMoneyThisMonth());
    }

    @GetMapping("/payment-slips")
    public ResponseEntity<Double> totalPaymentSlips() {
        return ResponseEntity.ok(statisticalService.totalPaymentSlipsThisMonth());
    }

    @GetMapping("/profit-loss")
    public ResponseEntity<Map<String, Double>> profitLossOverYear() {
        return ResponseEntity.ok(statisticalService.profitLossOverYear());
    }

    @GetMapping("/worker-salary")
    public ResponseEntity<Double> totalWorkerSalary() {
        return ResponseEntity.ok(statisticalService.totalWorkerSalaryThisMonth());
    }
}
