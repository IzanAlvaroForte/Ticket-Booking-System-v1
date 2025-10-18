# Ticket Booking System v1 - Complete Documentation

## Project Overview
The Ticket Booking System is a comprehensive Spring Boot application designed to manage event ticket bookings, customer registrations, and event management. This system provides a robust RESTful API for handling the complete lifecycle of ticket booking operations, from event creation to customer check-ins.

## System Architecture
Built on Spring Boot 3.5.6 with Java 25, this application follows a layered architecture pattern with clear separation of concerns. The system utilizes PostgreSQL for data persistence and employs JPA/Hibernate for object-relational mapping. The project is structured with distinct packages for controllers, services, repositories, entities, and DTOs, ensuring maintainable and scalable code organization.

## Core Features

### Customer Management
The system provides complete customer lifecycle management, allowing registration, profile updates, and retrieval of customer information. Customers can be created with unique usernames and email addresses, with built-in validation for phone numbers and email formats. The API supports paginated retrieval of all registered customers for efficient data management.

### Event Management
Event organizers can create, view, update, and cancel events through a comprehensive set of endpoints. Each event includes detailed information such as name, description, venue, date, seating capacity, ticket pricing, and category classification. The system automatically tracks available seats and prevents overbooking through real-time seat availability checks.

### Ticket Booking Engine
The heart of the system is its sophisticated ticket booking functionality. Customers can book tickets for specific events, selecting preferred seats when available. The booking process generates unique ticket numbers, handles payment calculations, and manages booking status transitions. The system supports various ticket operations including confirmation, cancellation, refund processing, check-in, and even ticket transfers between customers.

## Business Logic and Validation
The application implements extensive business rules including seat availability verification, duplicate booking prevention, event date validation, and status transition controls. Ticket refunds are only permitted for confirmed tickets before event start times, while transfers are restricted to active tickets that haven't been used or refunded. The system includes comprehensive error handling with meaningful error messages for all operational scenarios.

## API Design
All endpoints follow RESTful conventions with appropriate HTTP methods and status codes. The API uses Data Transfer Objects (DTOs) for clean separation between persistence models and API contracts. Pagination support is implemented for list endpoints to ensure optimal performance with large datasets. The system maintains consistent naming conventions and URL structures across all resources.

## Data Management
The application employs Spring Data JPA for efficient database operations with built-in transaction management. Entity relationships are properly mapped with appropriate JPA annotations, ensuring data integrity through foreign key constraints and cascade operations. Unique constraints prevent duplicate bookings and ensure data consistency across all operations.

This ticket booking system represents a production-ready foundation for event management platforms, with extensible architecture that can accommodate additional features such as payment integration, email notifications, and advanced reporting capabilities.
