package com.example.moviebookingticket.exception;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String message){
      super(message);
  }

}
