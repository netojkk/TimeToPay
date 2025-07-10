package com.neto.TimeToPay.controller;

import com.neto.TimeToPay.dto.*;
import com.neto.TimeToPay.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponseDTO> singUpClientClient(@RequestBody RegisterRequestDTO body){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.singUpClient(body));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginClient(@RequestBody LoginRequestDTO body){
        return ResponseEntity.ok(clientService.loginClient(body));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(){
        List<ClientResponseDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
}
