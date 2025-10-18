package com.example.Online_Ticket_Booking_System.DTO.EventsDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventsResponseChooseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private String venue;
    private Integer totalSeats;
    private Integer availableSeats;
    private BigDecimal ticketPrice;
    private String category;

    public EventsResponseChooseDTO(Long id, String name, String description, LocalDateTime eventDate, String venue, Integer totalSeats, BigDecimal ticketPrice, Integer availableSeats, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public String getCategory() {
        return category;
    }
}
