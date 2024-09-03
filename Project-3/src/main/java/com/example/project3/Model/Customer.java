package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Integer id;
    @NotEmpty(message = "phone number Cannot be null.")
//    @Pattern(regexp = "",message = "Must start with \"05\"")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Account> accounts;

}
