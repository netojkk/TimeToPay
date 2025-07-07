package com.neto.TimeToPay.services;

import com.neto.TimeToPay.domain.Client;
import com.neto.TimeToPay.dto.RegisterRequestDTO;
import com.neto.TimeToPay.dto.RegisterResponseDTO;
import com.neto.TimeToPay.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    private RegisterResponseDTO createClient(RegisterRequestDTO data){
        if (clientRepository.findByEmail(data.email()).isPresent()){
            throw new RuntimeException("Email already exists!");
        }

        Client client = new Client(data);
        clientRepository.save(client);
        return new RegisterResponseDTO(client.getId(), client.getName(), client.getEmail());
    }


}
