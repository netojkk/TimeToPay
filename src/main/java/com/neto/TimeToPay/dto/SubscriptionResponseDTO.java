package com.neto.TimeToPay.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SubscriptionResponseDTO(Long id, String name, BigDecimal value, LocalDate dateSigned, LocalDate dueDate, boolean active) {
}
