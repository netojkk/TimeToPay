package com.neto.TimeToPay.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "subscription")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal value;
    private LocalDate dateSigned;
    private LocalDate expirationDate;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


}
