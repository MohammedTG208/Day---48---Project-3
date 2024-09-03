package com.example.project3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTO {
    private Integer employeeId;
    @NotEmpty(message = "username can not be null")
    @Size(min = 4,max = 10,message = "Length must be between 4 and 10 characters")
    private String username;
    @NotEmpty(message = "password can not be null")
    @Size(max = 40,min = 6,message = "Length must be at least 6 characters. ")
    private String password;
    @NotEmpty(message = "name can not be null")
    @Size(min = 2,max = 20,message = "Length must be between 2 and 20 characters. ")
    private String name;
    @Email(message = "Must be a valid email format")
    private String email;
    private String role;
    @NotEmpty(message = "position can not be null")
    private String position;
    @Positive(message = "enter valid value for salary")
    private double salary;

}
