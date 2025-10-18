package com.example.Online_Ticket_Booking_System.DTO.CustomerDTO;

import java.time.LocalDateTime;

public class CustomerUpdateResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNum;
    private LocalDateTime createdAt;

    public CustomerUpdateResponseDTO(Long id, String username, String email, String phoneNum, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
