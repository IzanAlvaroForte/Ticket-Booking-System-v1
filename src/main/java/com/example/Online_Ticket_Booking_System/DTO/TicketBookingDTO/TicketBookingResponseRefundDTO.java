package com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO;

import java.time.LocalDateTime;

public class TicketBookingResponseRefundDTO {
    private Long id;
    private String ticketNumber;
    private String status;
    private LocalDateTime refundAt;

    public TicketBookingResponseRefundDTO(Long id, String ticketNumber, String status, LocalDateTime refundAt) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.status = status;
        this.refundAt = refundAt;
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

    public LocalDateTime getRefundAt() {
        return refundAt;
    }
}
