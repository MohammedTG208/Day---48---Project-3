package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Employee {
    @Id
    private Integer id;
    @NotEmpty(message = "position can not be null")
    @Column(columnDefinition = "varchar(40) not null")
    private String position;
    @Positive(message = "enter valid value for salary")
    @Column(columnDefinition = "decimal(10,2) not null")
    private double salary;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;
}
