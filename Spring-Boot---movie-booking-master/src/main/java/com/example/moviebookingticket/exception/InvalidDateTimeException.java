package com.example.moviebookingticket.exception;

public class InvalidDateTimeException extends RuntimeException{
    public InvalidDateTimeException(String errormessage){
        super(errormessage);
    }
}
