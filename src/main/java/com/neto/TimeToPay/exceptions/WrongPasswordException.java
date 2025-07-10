package com.neto.TimeToPay.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message){ super(message);}
}
