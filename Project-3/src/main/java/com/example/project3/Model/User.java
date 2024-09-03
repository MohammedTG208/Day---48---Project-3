package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username can not be null")
    @Column(columnDefinition = "varchar(30) not null unique")
    @Size(min = 4,max = 10,message = "Length must be between 4 and 10 characters")
    private String username;
    @NotEmpty(message = "password can not be null")
    @Column(columnDefinition = "varchar(255) not null")
//    @Size(max = 40,min = 6,message = "Length must be at least 6 characters. ")
    private String password;
    @NotEmpty(message = "name can not be null")
    @Column(columnDefinition = "varchar(20) not null")
    @Size(min = 2,max = 20,message = "Length must be between 2 and 20 characters. ")
    private String name;
    @Email(message = "Must be a valid email format")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;
    @Column(columnDefinition = "enum('CUSTOMER','EMPLOYEE','ADMIN')")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
