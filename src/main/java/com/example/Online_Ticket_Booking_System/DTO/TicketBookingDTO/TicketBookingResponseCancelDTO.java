package com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO;

import java.time.LocalDateTime;

public class TicketBookingResponseCancelDTO {
    private Long id;
    private String ticketNumber;
    private String status;
    private LocalDateTime createdAt;

    public TicketBookingResponseCancelDTO(Long id, String ticketNumber, String status, LocalDateTime createdAt) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.status = status;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
