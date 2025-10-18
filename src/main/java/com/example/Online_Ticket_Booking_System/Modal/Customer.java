package com.example.Online_Ticket_Booking_System.Modal;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer_table")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String phoneNum;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "customer")
    private List<TicketBooking> ticketBookings;

    public Customer() {
    }

    public Customer(Long id,
                    String username,
                    String email,
                    List<TicketBooking> ticketBookings,
                    LocalDateTime createdAt,
                    String phoneNum) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.ticketBookings = ticketBookings;
        this.createdAt = createdAt;
        this.phoneNum = phoneNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<TicketBooking> getTicketBookings() {
        return ticketBookings;
    }

    public void setTicketBookings(List<TicketBooking> ticketBookings) {
        this.ticketBookings = ticketBookings;
    }
}
