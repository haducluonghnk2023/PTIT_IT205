package com.data.session04.controller;

import com.data.session04.model.entity.Booking;
import com.data.session04.model.entity.Flight;
import com.data.session04.services.impl.BookingService;
import com.data.session04.services.impl.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightServiceImpl flightService;
    @Autowired
    private BookingService bookingService;

    @GetMapping 
    public String listFlights(Model model,
                              @RequestParam(defaultValue = "") String departure,
                              @RequestParam(defaultValue = "") String destination,
                              @RequestParam(defaultValue = "0") int page) {
        Page<Flight> flights = flightService.searchFlights(departure, destination,
                PageRequest.of(page, 5, Sort.by("id").descending()));
        model.addAttribute("flights", flights);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        return "flights"; // giao diện Thymeleaf
    }

    @PostMapping("/book")
    public String bookFlight(@ModelAttribute Booking booking) {
        bookingService.bookFlight(booking);
        return "redirect:/bookings?phone=" + booking.getCustomerPhone();
    }
}
