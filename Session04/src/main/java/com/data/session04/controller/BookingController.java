package com.data.session04.controller;

import com.data.session04.services.impl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String viewBookings(@RequestParam("phone") String phone, Model model) {
        model.addAttribute("bookings", bookingService.getBookingsByPhone(phone));
        model.addAttribute("phone", phone);
        return "bookings";
    }

    @PostMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id, @RequestParam("phone") String phone) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings?phone=" + phone;
    }
}
