package com.example.Online_Ticket_Booking_System.DTO.EventsDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventsResponseViewDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private String venue;
    private BigDecimal ticketPrice;

    public EventsResponseViewDTO(Long id, String name, String description, LocalDateTime eventDate, String venue, BigDecimal ticketPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.venue = venue;
        this.ticketPrice = ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }
}
