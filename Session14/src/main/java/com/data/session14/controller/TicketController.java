package com.data.session14.controller;

import com.data.session14.modal.dto.req.TicketRequest;
import com.data.session14.modal.dto.res.APIResponse;
import com.data.session14.modal.entity.Ticket;
import com.data.session14.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Đặt vé (USER)
    @PostMapping("/tickets/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse<Ticket>> bookTicket(@RequestBody TicketRequest request,
                                                          @AuthenticationPrincipal UserDetails userDetails) {
        Ticket ticket = ticketService.bookTicket(request, userDetails.getUsername());
        return ResponseEntity.ok(new APIResponse<>("Đặt vé thành công", ticket, true, HttpStatus.CREATED));
    }

    // Xem vé cá nhân (USER)
    @GetMapping("/tickets/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse<List<Ticket>>> getMyTickets(@AuthenticationPrincipal UserDetails userDetails) {
        List<Ticket> tickets = ticketService.getMyTickets(userDetails.getUsername());
        return ResponseEntity.ok(new APIResponse<>("Lấy vé cá nhân thành công", tickets, true, HttpStatus.OK));
    }

    // ADMIN xem tất cả vé
    @GetMapping("/admin/tickets")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<APIResponse<List<Ticket>>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(new APIResponse<>("Lấy danh sách vé thành công", tickets, true, HttpStatus.OK));
    }
}
