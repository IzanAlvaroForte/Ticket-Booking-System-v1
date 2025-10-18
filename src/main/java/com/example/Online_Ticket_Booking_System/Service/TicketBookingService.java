package com.example.Online_Ticket_Booking_System.Service;

import com.example.Online_Ticket_Booking_System.DTO.TicketBookingDTO.*;
import com.example.Online_Ticket_Booking_System.Modal.Customer;
import com.example.Online_Ticket_Booking_System.Modal.Event;
import com.example.Online_Ticket_Booking_System.Modal.TicketBooking;
import com.example.Online_Ticket_Booking_System.Repository.CustomerRepo;
import com.example.Online_Ticket_Booking_System.Repository.EventRepo;
import com.example.Online_Ticket_Booking_System.Repository.TicketBookingRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketBookingService {

    public final TicketBookingRepo ticketBookingRepo;
    public final EventRepo eventRepo;
    public final CustomerRepo customerRepo;
    public TicketBookingService(TicketBookingRepo ticketBookingRepo,
                                EventRepo eventRepo,
                                CustomerRepo customerRepo) {
        this.ticketBookingRepo = ticketBookingRepo;
        this.eventRepo = eventRepo;
        this.customerRepo = customerRepo;
    }

    @Transactional
    public TicketBookingResponseGiveDTO givingTicket(TicketBookingRequestDTO
                                                                 ticketBookingRequestDTO) {

        Event findEvent = eventRepo.findById(ticketBookingRequestDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Can't find existing event"));

        Customer findCustomer = customerRepo.findById(ticketBookingRequestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Can't find existing customer"));

        if (findEvent.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        TicketBooking ticketBooking = new TicketBooking();
        ticketBooking.setSeatNumber(ticketBookingRequestDTO.getSeatNumber());
        ticketBooking.setEvent(findEvent);
        ticketBooking.setCustomer(findCustomer);
        ticketBooking.setPrice(findEvent.getTicketPrice());
        ticketBooking.setStatus("CONFIRMED");
        ticketBooking.setTicketNumber(generateTicketNumber());
        ticketBooking.setBookingTime(LocalDateTime.now());
        ticketBooking.setCreatedAt(LocalDateTime.now());

        findEvent.setAvailableSeats(findEvent.getAvailableSeats() - 1);
        eventRepo.save(findEvent);

        TicketBooking savedBooking = ticketBookingRepo.save(ticketBooking);
        return new TicketBookingResponseGiveDTO(
                savedBooking.getId(),
                savedBooking.getTicketNumber(),
                savedBooking.getSeatNumber(),
                savedBooking.getPrice(),
                savedBooking.getStatus(),
                findEvent.getName(),
                savedBooking.getBookingTime(),
                findEvent.getEventDate(),
                findEvent.getVenue(),
                findCustomer.getUsername(),
                findCustomer.getEmail()
        );
    }

    @Transactional
    public TicketBookingResponseCancelDTO cancelTicket(Long id) {
        TicketBooking ticket =ticketBookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find this ticket"));

        ticket.setStatus("CACELLED");

        Event event = ticket.getEvent();
        event.setAvailableSeats(event.getAvailableSeats() + 1);
        eventRepo.save(event);

        TicketBooking cancelledTicket = ticketBookingRepo.save(ticket);

        return new TicketBookingResponseCancelDTO(
                cancelledTicket.getId(),
                cancelledTicket.getTicketNumber(),
                cancelledTicket.getStatus(),
                cancelledTicket.getCreatedAt()
        );
    }

    @Transactional
    public TicketBookingResponseConfirmDTO confirmTicket(Long id) {
        TicketBooking ticket = ticketBookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find confirm Id"));

        if ("CONFIRMED".equals(ticket.getStatus())) {
            throw new RuntimeException("This is already a confirmed ticket");
        }

        ticket.setStatus("CONFIRMED");
        ticket.setConfirmedAt(LocalDateTime.now());

        TicketBooking confirmedTicket = ticketBookingRepo.save(ticket);

        return new TicketBookingResponseConfirmDTO(
                confirmedTicket.getId(),
                confirmedTicket.getTicketNumber(),
                confirmedTicket.getConfirmedAt(),
                confirmedTicket.getStatus()
        );
    }

    @Transactional
    public TicketBookingResponseRefundDTO refundTicket(Long id) {
        TicketBooking ticket = ticketBookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find refund Id"));

        if ("CANCELLED".equals(ticket.getStatus()) || "REFUNDED".equals(ticket.getStatus())) {
            throw new RuntimeException("Ticket already cancelled or refunded");
        }

        if (!"CONFIRMED".equals(ticket.getStatus())) {
            throw new RuntimeException("Only confirmed tickets can be refunded");
        }

        if (ticket.getEvent().getEventDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot refund after event has started");
        }

        ticket.setStatus("REFUNDED");
        ticket.setCreatedAt(LocalDateTime.now());

        TicketBooking refundedTicket = ticketBookingRepo.save(ticket);

        if (!"REFUNDED".equals(refundedTicket.getStatus())) {
            throw new RuntimeException("Ticket is not yet refunded");
        }

        return new TicketBookingResponseRefundDTO(
                refundedTicket.getId(),
                refundedTicket.getTicketNumber(),
                refundedTicket.getStatus(),
                refundedTicket.getCancelledAt()
        );
    }

    @Transactional
    public TicketBookingResponseCheckInTicketDTO checkingTicket(Long id) {
        TicketBooking ticket = ticketBookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cant Find Ticket To Check Status"));

        if ("CANCELLED".equals(ticket.getStatus())) {
            throw new RuntimeException("Cannot check in - ticket is cancelled");
        }

        if ("REFUNDED".equals(ticket.getStatus())) {
            throw new RuntimeException("Cannot check in - ticket is refunded");
        }

        if ("CHECKED_IN".equals(ticket.getStatus())) {
            throw new RuntimeException("Ticket already checked in");
        }

        ticket.setStatus("CHECKED_IN");
        ticket.setCheckedInAt(LocalDateTime.now());

        TicketBooking savedCheckInTicket = ticketBookingRepo.save(ticket);

        return new TicketBookingResponseCheckInTicketDTO(
                savedCheckInTicket.getId(),
                savedCheckInTicket.getTicketNumber(),
                savedCheckInTicket.getStatus(),
                savedCheckInTicket.getCheckedInAt()
        );
    }

    @Transactional
    public TicketBookingResponseTransferToDTO transferTicket(Long ticketId, Long customerId) {
        TicketBooking findTicketId = ticketBookingRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Can't find the existing ticket"));
        Customer findCustomerId = customerRepo.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Can't find existing customer"));

        if ("CANCELLED".equals(findTicketId.getStatus())) {
            throw new RuntimeException("Cannot transfer cancelled ticket");
        }

        if ("REFUNDED".equals(findTicketId.getStatus())) {
            throw new RuntimeException("Cannot transfer refunded ticket");
        }

        if ("CHECKED_IN".equals(findTicketId.getStatus())) {
            throw new RuntimeException("Cannot transfer used ticket");
        }

        if (findTicketId.getEvent().getEventDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot transfer after event started");
        }

        Customer oldCustomer = findTicketId.getCustomer();
        findTicketId.setCustomer(findCustomerId);
        findTicketId.setStatus("TRANSFERRED");
        findTicketId.setCreatedAt(LocalDateTime.now());

        TicketBooking savedTransferTicket = ticketBookingRepo.save(findTicketId);

        return new TicketBookingResponseTransferToDTO(
                savedTransferTicket.getId(),
                savedTransferTicket.getTicketNumber(),
                savedTransferTicket.getStatus(),
                oldCustomer.getUsername(),
                findCustomerId.getUsername(),
                savedTransferTicket.getCreatedAt()
        );
    }


    private String generateTicketNumber() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
