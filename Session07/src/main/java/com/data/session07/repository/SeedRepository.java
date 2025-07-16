package com.data.session07.repository;

import com.data.session07.model.entity.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedRepository extends JpaRepository<Seed,Long> {
    @Query("SELECT SUM(s.quantity) FROM Seed s")
    int sumAllQuantity();
}
