package com.example.Online_Ticket_Booking_System.Controller;

import com.example.Online_Ticket_Booking_System.DTO.EventsDTO.EventsRequestDTO;
import com.example.Online_Ticket_Booking_System.DTO.EventsDTO.EventsResponseChooseDTO;
import com.example.Online_Ticket_Booking_System.DTO.EventsDTO.EventsResponseViewDTO;
import com.example.Online_Ticket_Booking_System.Service.EventService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/Events")
public class EventController {

    public final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventsResponseChooseDTO> chooseEvents(@RequestBody EventsRequestDTO eventsRequestDTO) {
        EventsResponseChooseDTO savedChooseEvents = eventService.chooseEvent(eventsRequestDTO);
        return ResponseEntity.ok(savedChooseEvents);
    }

    @GetMapping(path = "/view_all")
    public ResponseEntity<List<EventsResponseViewDTO>> viewAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                eventService.userViewAllEvents(page, size));
    }

    @DeleteMapping(path = "/event/{id}")
    public ResponseEntity<?> cancelEvents(@PathVariable Long id) {
        eventService.cancelEvent(id);
        return ResponseEntity.ok("Events successfully deleted");
    }

    @GetMapping(path = "/{id}/event")
    public ResponseEntity<EventsResponseChooseDTO> viewEvent(@PathVariable Long id) {
        EventsResponseChooseDTO viewEventResult = eventService.viewEvent(id);
        return ResponseEntity.ok(viewEventResult);
    }

}
