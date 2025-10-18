package com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO;


public class TicketBookingRequestDTO {
    private String seatNumber;
    private Long eventId;
    private Long customerId;

    public TicketBookingRequestDTO() {
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
