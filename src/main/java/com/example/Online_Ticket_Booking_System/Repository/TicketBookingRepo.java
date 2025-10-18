package com.example.Online_Ticket_Booking_System.Repository;

import com.example.Online_Ticket_Booking_System.Modal.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketBookingRepo extends JpaRepository<TicketBooking, Long> {
    List<TicketBooking> findByEventId(Long eventId);
    List<TicketBooking> findByCustomerId(Long customerId);
    List<TicketBooking> findByStatus(String status);
}
