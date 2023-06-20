package com.example.moviebookingticket.exception;

public class RuleDeleteException extends RuntimeException {
    public RuleDeleteException (String errormessage){
        super(errormessage);
    }
}
