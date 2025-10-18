package com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO;

import java.time.LocalDateTime;

public class TicketBookingResponseConfirmDTO {
    private Long id;
    private String ticketNumber;
    private LocalDateTime confirmAt;
    private String status;

    public TicketBookingResponseConfirmDTO(Long id, String ticketNumber, LocalDateTime confirmAt, String status) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.confirmAt = confirmAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public LocalDateTime getConfirmAt() {
        return confirmAt;
    }

    public String getStatus() {
        return status;
    }
}
