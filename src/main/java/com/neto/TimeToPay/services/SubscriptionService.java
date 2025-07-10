package com.neto.TimeToPay.services;

import com.neto.TimeToPay.domain.Client;
import com.neto.TimeToPay.domain.Subscription;
import com.neto.TimeToPay.dto.SubscriptionRequestDTO;
import com.neto.TimeToPay.dto.SubscriptionResponseDTO;
import com.neto.TimeToPay.dto.UpdateSubscriptionDTO;
import com.neto.TimeToPay.exceptions.DuplicationSubscriptionException;
import com.neto.TimeToPay.exceptions.SubscriptionNotFoundException;
import com.neto.TimeToPay.exceptions.UserNotFoundException;
import com.neto.TimeToPay.exceptions.WithoutDateException;
import com.neto.TimeToPay.repositories.ClientRepository;
import com.neto.TimeToPay.repositories.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class SubscriptionService {

    private SubscriptionRepository repository;
    private ClientRepository clientRepository;

    public SubscriptionResponseDTO createSubscription(Long clientId, SubscriptionRequestDTO data){
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new UserNotFoundException("User Not Found!"));

        Boolean exist = repository.existsByNameAndClientId(data.name(), clientId);
        if (exist){
            throw new DuplicationSubscriptionException("Subscription Already exist!");
        }

        LocalDate expiration;

        if (data.manualExpirationDate() != null){
            expiration = data.manualExpirationDate();
        }else if (data.subscriptionTime() != null){
            expiration = data.dateSigned().plusMonths(data.subscriptionTime().getMonths());
        }else {
            throw new WithoutDateException("You must define a expiration date!");
        }

        Subscription subscription = new Subscription();
        subscription.setName(data.name());
        subscription.setValue(data.value());
        subscription.setDateSigned(data.dateSigned());
        subscription.setExpirationDate(expiration);
        subscription.setActive(true);
        subscription.setClient(client);

        repository.saveAndFlush(subscription);

        return new SubscriptionResponseDTO(
                subscription.getId(),
                subscription.getName(),
                subscription.getValue(),
                subscription.getDateSigned(),
                subscription.getExpirationDate(),
                subscription.getActive());
    }


    public List<SubscriptionResponseDTO> getAllSubs(Long clientId){
        if (clientId == null){
            throw new UserNotFoundException("User Not Found");
        }
        List<Subscription> subscriptions = repository.findByClientId(clientId);

        return subscriptions.stream()
                .map(sub ->
                        new SubscriptionResponseDTO
                                (sub.getId(),
                                        sub.getName(),
                                        sub.getValue(),
                                        sub.getDateSigned(),
                                        sub.getExpirationDate(),
                                        sub.getActive())
                ).toList();

    }

    public SubscriptionResponseDTO updateSubscription(Long clientId, UpdateSubscriptionDTO data){
        Subscription subscription = repository.findById(data.id())
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription Not Found!"));

        Client client = clientRepository.findById(clientId).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        subscription.setName(data.name() != null ? data.name() : subscription.getName());
        subscription.setValue(data.value() != null ? data.value() : subscription.getValue());
        subscription.setDateSigned(data.dateSigned() != null ? data.dateSigned() : subscription.getDateSigned());

        LocalDate expiration;
        if (data.manualExpirationDate() != null){
            expiration = data.manualExpirationDate();
        }else if (data.subscriptionTime() != null){
            expiration = data.dateSigned().plusMonths(data.subscriptionTime().getMonths());
        }else {
            throw new WithoutDateException("You must define a expiration date!");
        }
        subscription.setExpirationDate(expiration);

        repository.saveAndFlush(subscription);

        return new SubscriptionResponseDTO(
                subscription.getId(),
                subscription.getName(),
                subscription.getValue(),
                subscription.getDateSigned(),
                subscription.getExpirationDate(),
                subscription.getActive());
    }
}
