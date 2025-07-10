package com.neto.TimeToPay.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorMessage {

    private HttpStatus status;
    private String message;
}
