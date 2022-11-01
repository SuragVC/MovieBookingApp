package com.movie.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExcepionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails>allExcepions(Exception exception,WebRequest req){
		ErrorDetails error=new ErrorDetails();
		error.setDetails(exception.getMessage());
		error.setMessage(req.getDescription(false));
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MoviesExceptions.class)
	public ResponseEntity<ErrorDetails>movieExcepions(MoviesExceptions exception,WebRequest req){
		ErrorDetails error=new ErrorDetails();
		error.setDetails(exception.getMessage());
		error.setMessage(req.getDescription(false));
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ConnectorServiceException.class)
	public ResponseEntity<ErrorDetails>connectorServiceExceptionExcepions(ConnectorServiceException exception,WebRequest req){
		ErrorDetails error=new ErrorDetails();
		error.setDetails(exception.getMessage());
		error.setMessage(req.getDescription(false));
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TicketException.class)
	public ResponseEntity<ErrorDetails>ticketException(TicketException exception,WebRequest req){
		ErrorDetails error=new ErrorDetails();
		error.setDetails(exception.getMessage());
		error.setMessage(req.getDescription(false));
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
}
