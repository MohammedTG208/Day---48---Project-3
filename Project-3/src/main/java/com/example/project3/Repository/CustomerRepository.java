package com.example.project3.Repository;

import com.example.project3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerById(Integer id);
//    SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
//    FROM Orders
//    INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;
//    @Query("select cus from Customer cus inner JOIN User on cus.user.id=User.id")
//    List findAllCustomersAndUserInfo();

//    List<Customer> findAllCustomers();
}
