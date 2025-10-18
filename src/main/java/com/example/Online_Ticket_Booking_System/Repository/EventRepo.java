package com.example.Online_Ticket_Booking_System.Repository;

import com.example.Online_Ticket_Booking_System.Modal.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
}
