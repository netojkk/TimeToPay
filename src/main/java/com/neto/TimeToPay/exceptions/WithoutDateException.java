package com.neto.TimeToPay.exceptions;

public class WithoutDateException extends RuntimeException {
    public WithoutDateException(String message) {
        super(message);
    }
}
