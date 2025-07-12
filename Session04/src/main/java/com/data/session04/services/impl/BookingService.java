package com.data.session04.services.impl;

import com.data.session04.model.entity.Booking;
import com.data.session04.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public void bookFlight(Booking booking) {
        booking.setBookingTime(new Date());
        booking.setStatus("BOOKED");
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByPhone(String phone) {
        return bookingRepository.findByCustomerPhone(phone);
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
        }
    }
}
