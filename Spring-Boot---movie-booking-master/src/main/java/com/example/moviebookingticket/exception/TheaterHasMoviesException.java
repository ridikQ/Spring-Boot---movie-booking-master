package com.example.moviebookingticket.exception;

public class TheaterHasMoviesException extends RuntimeException {
    public TheaterHasMoviesException(String message) {
        super(message);
    }
}