package com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO;

import java.time.LocalDateTime;

public class TicketBookingResponseTransferToDTO {
    private Long id;
    private String ticketNumber;
    private String status;
    private String fromCustomer;
    private String toCustomer;
    private LocalDateTime transferredAt;

    public TicketBookingResponseTransferToDTO(Long id, String ticketNumber, String status, String fromCustomer, String toCustomer, LocalDateTime transferredAt) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.status = status;
        this.fromCustomer = fromCustomer;
        this.toCustomer = toCustomer;
        this.transferredAt = transferredAt;
    }

    public Long getId() {
        return id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getFromCustomer() {
        return fromCustomer;
    }

    public String getToCustomer() {
        return toCustomer;
    }

    public LocalDateTime getTransferredAt() {
        return transferredAt;
    }
}
