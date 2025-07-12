package com.data.session04.services.impl;

import com.data.session04.model.entity.Flight;
import com.data.session04.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl {
    @Autowired
    private FlightRepository flightRepository;

    public Page<Flight> searchFlights(String departure, String destination, Pageable pageable) {
        return flightRepository.findByDepartureContainingIgnoreCaseAndDestinationContainingIgnoreCase(departure, destination, pageable);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }
}
