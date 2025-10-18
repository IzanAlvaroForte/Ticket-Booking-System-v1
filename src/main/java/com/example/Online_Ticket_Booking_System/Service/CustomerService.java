package com.example.Online_Ticket_Booking_System.Service;

import com.example.Online_Ticket_Booking_System.DTO.CustomerDTO.CustomerRequestDTO;
import com.example.Online_Ticket_Booking_System.DTO.CustomerDTO.CustomerCreateResponseDTO;
import com.example.Online_Ticket_Booking_System.DTO.CustomerDTO.CustomerUpdateResponseDTO;
import com.example.Online_Ticket_Booking_System.Modal.Customer;
import com.example.Online_Ticket_Booking_System.Repository.CustomerRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public CustomerCreateResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO.getPhoneNum().length() > 12 ) {
            throw new RuntimeException("Phone number can't exceed to 11");
        }

        if (!customerRequestDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new RuntimeException("Invalid email format");
        }

        Customer customer = new Customer();
        customer.setUsername(customerRequestDTO.getUsername());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setPhoneNum(customerRequestDTO.getPhoneNum());
        customer.setCreatedAt(LocalDateTime.now());

        Customer savedCustomer = customerRepo.save(customer);

        return new CustomerCreateResponseDTO(
                savedCustomer.getId(),
                savedCustomer.getUsername(),
                savedCustomer.getEmail(),
                savedCustomer.getCreatedAt()
        );
    }

    public List<CustomerCreateResponseDTO> getAllCreatedCustomer(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepo.findAll(pageable);

        return customerPage.getContent().stream()
                .map(customer -> new CustomerCreateResponseDTO(
                        customer.getId(),
                        customer.getUsername(),
                        customer.getEmail(),
                        customer.getCreatedAt()
                )).collect(Collectors.toList());
    }

    @Transactional
    public CustomerUpdateResponseDTO updateCustomer(Long customerId,
                                                    CustomerRequestDTO customerRequestDTO) {

        Customer customer = customerRepo.findById(customerId).
                orElseThrow(() -> new RuntimeException("No customer found!"));
        customer.setUsername(customerRequestDTO.getUsername());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setPhoneNum(customerRequestDTO.getPhoneNum());

        Customer updatedCustomer = customerRepo.save(customer);

        return new CustomerUpdateResponseDTO(
                updatedCustomer.getId(),
                updatedCustomer.getUsername(),
                updatedCustomer.getEmail(),
                updatedCustomer.getPhoneNum(),
                updatedCustomer.getCreatedAt()
        );

    }


}
