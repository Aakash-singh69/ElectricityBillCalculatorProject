package com.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
@RestController
public class CustomExceptionHandler {

	public final ResponseEntity<ExceptionResponse> handleException(BillNotFoundException billNotFoundException, WebRequest request){
		
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), billNotFoundException.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value= ConsumerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> ConsumerNotFound(ConsumerNotFoundException consumerNotFoundException, WebRequest request){
		
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), consumerNotFoundException.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);	
	}
	
	public final ResponseEntity<ExceptionResponse> adminNotFound(AdminNotFoundException adminNotFoundException, WebRequest request){
		
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), adminNotFoundException.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}
}
