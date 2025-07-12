package com.data.session04.repository;

import com.data.session04.model.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Page<Flight> findByDepartureContainingIgnoreCaseAndDestinationContainingIgnoreCase(
            String departure, String destination, Pageable pageable);
}
