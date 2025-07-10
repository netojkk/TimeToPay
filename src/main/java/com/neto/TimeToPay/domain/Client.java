package com.neto.TimeToPay.domain;

import com.neto.TimeToPay.dto.ClientResponseDTO;
import com.neto.TimeToPay.dto.RegisterRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "client")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    //public Client (Long id, ClientResponseDTO client) {
      //  this.id = client.id();
     //   this.name = client.name();
      //  this.email = client.email();
    //}

    public Client (RegisterRequestDTO client){
        this.name = client.name();
        this.email = client.email();
        this.password = client.password();
    }


}
