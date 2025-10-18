package com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO;

import java.time.LocalDateTime;

public class TicketBookingResponseCheckInTicketDTO {
    private Long id;
    private String ticketNumber;
    private String status;
    private LocalDateTime checkedInAt;

    public TicketBookingResponseCheckInTicketDTO(Long id, String ticketNumber, String status, LocalDateTime checkedInAt) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.status = status;
        this.checkedInAt = checkedInAt;
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

    public LocalDateTime getCheckedInAt() {
        return checkedInAt;
    }
}
