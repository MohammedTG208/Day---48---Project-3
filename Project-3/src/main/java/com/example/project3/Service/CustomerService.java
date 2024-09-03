package com.example.project3.Service;

import com.example.project3.API.APIException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;


    public void register(CustomerDTO customerDTO) {
        customerDTO.setRole("CUSTOMER");
        customerDTO.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        Customer customer=new Customer();
        customer.setId(null);
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        User user=new User(null, customerDTO.getUsername(), customerDTO.getPassword(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getRole(), customer,null);
        customer.setUser(user);
        authRepository.save(user);
        customerRepository.save(customer);
    }

    public void updateCustomer(CustomerDTO customerDTO,Integer customerID) {
        User olduser=authRepository.findUserById(customerID);
        Customer oldcustomer=customerRepository.findCustomerById(customerID);
        if (oldcustomer==null||olduser==null){
            throw new APIException("you can not update this account because not found");
        }else {
            oldcustomer.setPhoneNumber(oldcustomer.getPhoneNumber());
            olduser.setUsername(customerDTO.getUsername());
            olduser.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
            olduser.setCustomer(oldcustomer);
            oldcustomer.setUser(olduser);
            authRepository.save(olduser);
            customerRepository.save(oldcustomer);
        }
    }

    public void deleteCustomer(Integer customerID) {
        Customer oldCustomer=customerRepository.findCustomerById(customerID);
        User oldUser=authRepository.findUserById(customerID);
        if (oldCustomer==null||oldUser==null){
            throw new APIException("you can not delete this account because not found");
        }else {
            customerRepository.deleteById(customerID);
            authRepository.deleteById(customerID);
        }
    }

    public List getAllCustomer(Integer AdminId){
        User user=authRepository.findUserById(AdminId);
        if (user==null){
            throw new APIException("you can not get all customer because not found");
        }else {
            return customerRepository.findAll();
        }
    }
}
