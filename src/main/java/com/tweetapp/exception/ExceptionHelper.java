package com.tweetapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tweetapp.bean.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHelper {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> userAlreayExist(UserAlreadyExistsException ex, WebRequest request) {
		log.error("user already exists exception ");
		ErrorResponse errorResponse = ErrorResponse.builder().errMessage(ex.getMessage()).errDetails(ex.getMessage())
				.statusCode(HttpStatus.CONFLICT.value()).timeStamp(LocalDateTime.now()).build();
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> userNotFoundException(Exception ex, WebRequest request) {
		log.error("user not found exception");
		ErrorResponse errorResponse = ErrorResponse.builder().errMessage("user not found").errDetails(ex.getMessage())
				.statusCode(HttpStatus.NOT_FOUND.value()).timeStamp(LocalDateTime.now()).build();
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> customeExceptionHandler(Exception ex, WebRequest request) {
		log.error("Exception {} ", ex);
		ErrorResponse errorResponse = ErrorResponse.builder().errMessage("SomeThing went wrong !!!")
				.errDetails(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timeStamp(LocalDateTime.now()).build();
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
