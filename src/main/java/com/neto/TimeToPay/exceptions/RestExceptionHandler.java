package com.neto.TimeToPay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    private ResponseEntity<ApiErrorMessage> duplicateEmailHandler(DuplicateEmailException e){
        ApiErrorMessage threatResponse = new ApiErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);

    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ApiErrorMessage> userNotFoundHandler(UserNotFoundException e){
        ApiErrorMessage threatResponse = new ApiErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(WrongPasswordException.class)
    private ResponseEntity<ApiErrorMessage> wrongPasswordHandler(WrongPasswordException e){
        ApiErrorMessage threatResponse = new ApiErrorMessage(HttpStatus.UNAUTHORIZED, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(DuplicationSubscriptionException.class)
    private ResponseEntity<ApiErrorMessage> duplicationSubscriptionHandler(DuplicationSubscriptionException e){
        ApiErrorMessage threatResponse = new ApiErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    private ResponseEntity<ApiErrorMessage> subscriptionNotFoundHandler(SubscriptionNotFoundException e){
        ApiErrorMessage threatResponse = new ApiErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }
}
