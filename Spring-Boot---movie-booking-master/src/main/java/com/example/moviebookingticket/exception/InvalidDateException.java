package com.example.moviebookingticket.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String errormessage) {

        super(errormessage);
    }
}
