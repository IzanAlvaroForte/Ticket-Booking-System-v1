package com.example.Online_Ticket_Booking_System.Service;

import com.example.Online_Ticket_Booking_System.DTO.EventsDTO.EventsRequestDTO;
import com.example.Online_Ticket_Booking_System.DTO.EventsDTO.EventsResponseChooseDTO;
import com.example.Online_Ticket_Booking_System.DTO.EventsDTO.EventsResponseViewDTO;
import com.example.Online_Ticket_Booking_System.Modal.Event;
import com.example.Online_Ticket_Booking_System.Repository.EventRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepo eventRepo;
    public  EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public EventsResponseChooseDTO chooseEvent(EventsRequestDTO eventsRequestDTO) {

        if (eventsRequestDTO.getTotalSeats() <= 0) {
            throw new RuntimeException("Total seats unknown");
        }

        if (eventsRequestDTO.getTicketPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Ticket must be greater than 0");
        }

        if (eventsRequestDTO.getAvailableSeats() > eventsRequestDTO.getTotalSeats()) {
            throw new RuntimeException("Cannot exceeded the total seats");
        }

        Event events = new Event();
        events.setName(eventsRequestDTO.getName());
        events.setDescription(eventsRequestDTO.getDescription());
        events.setEventDate(eventsRequestDTO.getEventDate());
        events.setVenue(eventsRequestDTO.getVenue());
        events.setTotalSeats(eventsRequestDTO.getTotalSeats());
        events.setAvailableSeats(eventsRequestDTO.getAvailableSeats() != null ?
                eventsRequestDTO.getAvailableSeats() :
                eventsRequestDTO.getTotalSeats());
        events.setTicketPrice(eventsRequestDTO.getTicketPrice());
        events.setCategory(eventsRequestDTO.getCategory());
        events.setCreatedAt(LocalDateTime.now());
        events.setUpdatedAt(LocalDateTime.now());

        Event savedEvents = eventRepo.save(events);
        return new EventsResponseChooseDTO(
                savedEvents.getId(),
                savedEvents.getName(),
                savedEvents.getDescription(),
                savedEvents.getEventDate(),
                savedEvents.getVenue(),
                savedEvents.getTotalSeats(),
                savedEvents.getTicketPrice(),
                savedEvents.getAvailableSeats(),
                savedEvents.getCategory()
        );
    }

    public List<EventsResponseViewDTO> userViewAllEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventPage = eventRepo.findAll(pageable);

        return eventPage.getContent().stream()
                .map(event -> new EventsResponseViewDTO(
                        event.getId(),
                        event.getName(),
                        event.getDescription(),
                        event.getEventDate(),
                        event.getVenue(),
                        event.getTicketPrice()
                )).collect(Collectors.toList());
    }

    public void cancelEvent(Long id) {
        if (!eventRepo.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepo.deleteById(id);
    }

    public EventsResponseChooseDTO viewEvent(Long id) {
        Optional<Event> seeEvent = eventRepo.findById(id);

        if (seeEvent.isEmpty()) {
            throw new RuntimeException("Event not found with id: " + id);
        }

        Event savedEvent = seeEvent.get();

        return new EventsResponseChooseDTO(
                savedEvent.getId(),
                savedEvent.getName(),
                savedEvent.getDescription(),
                savedEvent.getEventDate(),
                savedEvent.getVenue(),
                savedEvent.getTotalSeats(),
                savedEvent.getTicketPrice(),
                savedEvent.getAvailableSeats(),
                savedEvent.getCategory()
        );
    }
}
