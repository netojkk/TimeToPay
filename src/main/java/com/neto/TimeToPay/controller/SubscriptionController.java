package com.neto.TimeToPay.controller;

import com.neto.TimeToPay.dto.SubscriptionRequestDTO;
import com.neto.TimeToPay.dto.SubscriptionResponseDTO;
import com.neto.TimeToPay.dto.UpdateSubscriptionDTO;
import com.neto.TimeToPay.services.SubscriptionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub")
@AllArgsConstructor
public class SubscriptionController {

    private SubscriptionService service;

    @PostMapping("/new/{id}")
    public ResponseEntity<SubscriptionResponseDTO> createSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionRequestDTO body){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createSubscription(id,body));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<SubscriptionResponseDTO>> getAll(@PathVariable Long clientId){
        List<SubscriptionResponseDTO> subs = service.getAllSubs(clientId);
        return ResponseEntity.ok(subs);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<SubscriptionResponseDTO> updateSubscription(@PathVariable Long clientId, @RequestBody UpdateSubscriptionDTO body){
        return ResponseEntity.ok(service.updateSubscription(clientId, body));
    }
}
