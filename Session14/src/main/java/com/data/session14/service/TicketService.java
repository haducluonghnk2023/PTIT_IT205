package com.data.session14.service;

import com.data.session14.modal.dto.req.TicketRequest;
import com.data.session14.modal.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(TicketRequest request, String username);
    List<Ticket> getMyTickets(String username);
    List<Ticket> getAllTickets();
}
