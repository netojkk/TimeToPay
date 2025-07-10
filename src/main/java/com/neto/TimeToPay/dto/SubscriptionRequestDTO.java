package com.neto.TimeToPay.dto;

import com.neto.TimeToPay.enums.SubscriptionTimeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Valid
public record SubscriptionRequestDTO(


        @NotBlank String name,
        @NotNull BigDecimal value,
        @NotNull LocalDate dateSigned,

        //Allowing the option of two date types: manual and predefined. With the @Validation
        SubscriptionTimeEnum subscriptionTime,
        LocalDate manualExpirationDate) {

}
