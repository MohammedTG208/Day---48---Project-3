package com.example.project3.Controller;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cus")
@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity registerNewCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body("Customer registered successfully");
    }
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @AuthenticationPrincipal User user) {
        customerService.updateCustomer(customerDTO, user.getId());
        return ResponseEntity.status(200).body("Customer updated successfully");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body("Customer deleted successfully");
    }

    @GetMapping("/get-all-cus-info")
    public ResponseEntity getAllCustomers(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(customerService.getAllCustomer(user.getId()));
    }

}
