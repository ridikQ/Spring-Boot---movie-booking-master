package com.example.moviebookingticket.exception;

public class MovieHasBookingException extends RuntimeException{
    public MovieHasBookingException(String message){
        super(message);
    }
}
