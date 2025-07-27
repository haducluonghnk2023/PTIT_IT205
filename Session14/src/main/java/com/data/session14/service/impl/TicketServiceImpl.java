package com.data.session14.service.impl;

import com.data.session14.modal.dto.req.TicketRequest;
import com.data.session14.modal.entity.ShowTime;
import com.data.session14.modal.entity.Ticket;
import com.data.session14.modal.entity.User;
import com.data.session14.repository.ShowTimeRepository;
import com.data.session14.repository.TicketRepository;
import com.data.session14.repository.UserRepository;
import com.data.session14.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Override
    public Ticket bookTicket(TicketRequest request, String username) {
        User user = userRepository.findByUsername(username);

        ShowTime showtime = showTimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new EntityNotFoundException("Showtime not found"));

        Ticket ticket = Ticket.builder()
                .user(user)
                .showTime(showtime)
                .seatNumber(request.getSeatNumber())
                .price(request.getPrice())
                .bookingTime(LocalDateTime.now())
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getMyTickets(String username) {
        return ticketRepository.findByUser_Username(username);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
