package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Account number can not be null")
    @Column(columnDefinition = "varchar(16) not null unique")
    @Pattern(regexp = "",message = "Must follow a specific format (\"XXXX-XXXX-XXXX-XXXX\"). ")
    private String accountNumber;
    @Positive(message = "enter vaild balance number")
    @Column(columnDefinition = "decimal(10,2) not null")
    private double balance;
    @Column(columnDefinition = "boolean default(false)")
    private boolean isActive;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
