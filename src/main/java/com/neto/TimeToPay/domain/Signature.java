package com.neto.TimeToPay.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "signature")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Signature {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate dateSigned;
    private LocalDate dueDate;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
