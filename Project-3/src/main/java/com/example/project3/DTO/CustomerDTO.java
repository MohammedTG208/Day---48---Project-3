package com.example.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {
    private Integer user_id;
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
    @NotEmpty(message = "phone number Cannot be null.")
//    @Pattern(regexp = "",message = "Must start with \"05\"")
    private String phoneNumber;
}
