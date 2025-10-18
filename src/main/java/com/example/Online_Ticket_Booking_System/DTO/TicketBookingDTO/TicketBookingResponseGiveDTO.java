package com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketBookingResponseGiveDTO {
    private Long id;
    private String ticketNumber;
    private String seatNumber;
    private BigDecimal price;
    private String status;
    private LocalDateTime bookingTime;
    private String eventName;
    private LocalDateTime eventDate;
    private String venue;
    private String customerName;
    private String customerEmail;

    public TicketBookingResponseGiveDTO(Long id, String ticketNumber, String seatNumber, BigDecimal price, String status, String eventName, LocalDateTime bookingTime, LocalDateTime eventDate, String venue, String customerName, String customerEmail) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
        this.eventName = eventName;
        this.bookingTime = bookingTime;
        this.eventDate = eventDate;
        this.venue = venue;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public Long getId() {
        return id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
