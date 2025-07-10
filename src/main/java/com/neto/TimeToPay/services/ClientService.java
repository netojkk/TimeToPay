package com.neto.TimeToPay.services;

import com.neto.TimeToPay.domain.Client;
import com.neto.TimeToPay.dto.*;
import com.neto.TimeToPay.exceptions.DuplicateEmailException;
import com.neto.TimeToPay.exceptions.UserNotFoundException;
import com.neto.TimeToPay.exceptions.WrongPasswordException;
import com.neto.TimeToPay.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public RegisterResponseDTO singUpClient(RegisterRequestDTO data){
        if (clientRepository.findByEmail(data.email()).isPresent()){
            throw new DuplicateEmailException("Email already exists!");
        }

        Client client = new Client(data);
        clientRepository.save(client);
        return new RegisterResponseDTO(client.getId(), client.getName(), client.getEmail());
    }

    public LoginResponseDTO loginClient(LoginRequestDTO data){
        Client client = this.clientRepository.findByEmail(data.email())
                .orElseThrow(() -> new UserNotFoundException("User Not Found!"));

        if(!data.password().equals(client.getPassword())){
            throw new WrongPasswordException("Wrong Password!");
        }

        return new LoginResponseDTO(client.getId(), client.getName(),client.getEmail());
    }

    public List<ClientResponseDTO> getAllClients(){
        return clientRepository.findAll().stream()
                .map(client -> new ClientResponseDTO(client.getId(), client.getName(), client.getEmail()))
                .toList();
    }
}
