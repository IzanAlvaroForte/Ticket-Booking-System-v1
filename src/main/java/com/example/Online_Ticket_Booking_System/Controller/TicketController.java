package com.example.Online_Ticket_Booking_System.Controller;

import com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO.*;
import com.example.Online_Ticket_Booking_System.Service.TicketBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/TicketBookings")
public class TicketController {

    public final TicketBookingService ticketBookingService;
    public TicketController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    @PostMapping
    public ResponseEntity<TicketBookingResponseGiveDTO> givenTicket(@RequestBody TicketBookingRequestDTO ticketBookingRequestDTO) {
        TicketBookingResponseGiveDTO givenTicketResult = ticketBookingService.givingTicket(ticketBookingRequestDTO);
        return ResponseEntity.ok(givenTicketResult);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<TicketBookingResponseCancelDTO> cancelTicket(@PathVariable Long id) {
        TicketBookingResponseCancelDTO result = ticketBookingService.cancelTicket(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<TicketBookingResponseConfirmDTO> confirmTicket(@PathVariable Long id) {
        TicketBookingResponseConfirmDTO result = ticketBookingService.confirmTicket(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/refund")
    public ResponseEntity<TicketBookingResponseRefundDTO> refundTicket(@PathVariable Long id) {
        TicketBookingResponseRefundDTO result = ticketBookingService.refundTicket(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/check-in")
    public ResponseEntity<TicketBookingResponseCheckInTicketDTO> checkInTicket(@PathVariable Long id) {
        TicketBookingResponseCheckInTicketDTO result = ticketBookingService.checkingTicket(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{ticketId}/transfer/{customerId}")
    public ResponseEntity<TicketBookingResponseTransferToDTO> transferTicket(
            @PathVariable Long ticketId, @PathVariable Long customerId) {
        TicketBookingResponseTransferToDTO result = ticketBookingService.transferTicket(ticketId, customerId);
        return ResponseEntity.ok(result);
    }
}

