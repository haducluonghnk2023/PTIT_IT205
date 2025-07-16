package com.data.session07.repository;

import com.data.session07.model.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    @Query("SELECT COALESCE(SUM(h.totalMoney), 0) FROM Harvest h WHERE h.createdAt BETWEEN :start AND :end")
    double sumTotalPriceByDateRange(LocalDate start, LocalDate end);
}
