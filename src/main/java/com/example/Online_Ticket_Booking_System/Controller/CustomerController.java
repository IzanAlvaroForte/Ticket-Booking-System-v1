package com.example.Online_Ticket_Booking_System.Controller;

import com.example.Online_Ticket_Booking_System.DTO.CustomerDTO.CustomerRequestDTO;
import com.example.Online_Ticket_Booking_System.DTO.CustomerDTO.CustomerCreateResponseDTO;
import com.example.Online_Ticket_Booking_System.DTO.CustomerDTO.CustomerUpdateResponseDTO;
import com.example.Online_Ticket_Booking_System.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerCreateResponseDTO> createCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerCreateResponseDTO createResult =  customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.ok(createResult);
    }

    @PutMapping(path = "/customer/{id}")
    public ResponseEntity<CustomerUpdateResponseDTO> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerUpdateResponseDTO updatedResult = customerService.updateCustomer(customerId, customerRequestDTO);
        return ResponseEntity.ok(updatedResult);
    }

    @GetMapping
    public ResponseEntity<List<CustomerCreateResponseDTO>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                customerService.getAllCreatedCustomer(page, size));
    }

}
