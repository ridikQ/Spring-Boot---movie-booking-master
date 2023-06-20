package com.example.moviebookingticket.exception;

import com.example.moviebookingticket.dto.ErrorFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@Component
@RestControllerAdvice
public class MyCustomExceptionHandler {

	 @ExceptionHandler(value = { CustomUserException.class })
		    protected ResponseEntity<Object> handleUserExceptions( RuntimeException ex) {
		       ErrorFormat errorBody=new ErrorFormat();
		       errorBody.setMessage(ex.getMessage());
			   errorBody.setTimeStamp(new Date());
			   errorBody.setPath("users/add");
			   errorBody.setSuggestion("Check the fields");

			   return new ResponseEntity<Object>(errorBody,HttpStatus.BAD_REQUEST);
		    }
	@ExceptionHandler(value = { InvalidDateException.class })
	protected ResponseEntity<Object> handleTimeTableExceptions( RuntimeException ex) {
		ErrorFormat errorBody=new ErrorFormat();
		errorBody.setMessage(ex.getMessage());
		errorBody.setTimeStamp(new Date());
		errorBody.setPath("timetables/add");

		return new ResponseEntity<Object>(errorBody,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = {RuleDeleteException.class })
	protected ResponseEntity<Object> handleTheaterExceptions( RuntimeException ex) {
		ErrorFormat errorBody=new ErrorFormat();
		errorBody.setMessage(ex.getMessage());
		errorBody.setTimeStamp(new Date());
		errorBody.setPath("timetables/delete");

		return new ResponseEntity<Object>(errorBody,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = {IllegalStateException.class })
	protected ResponseEntity<Object> handleMovieExceptions( RuntimeException ex) {
		ErrorFormat errorBody=new ErrorFormat();
		errorBody.setMessage(ex.getMessage());
		errorBody.setTimeStamp(new Date());
		errorBody.setPath("movies/add");
		errorBody.setSuggestion("Choose another name");

		return new ResponseEntity<Object>(errorBody,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = {InvalidDateTimeException.class })
	protected ResponseEntity<Object> handleBookingExceptions( RuntimeException ex) {
		ErrorFormat errorBody=new ErrorFormat();
		errorBody.setMessage(ex.getMessage());
		errorBody.setTimeStamp(new Date());
		errorBody.setPath("bookings/add");
		errorBody.setSuggestion("Contact admin");

		return new ResponseEntity<Object>(errorBody,HttpStatus.BAD_REQUEST);
	}







}