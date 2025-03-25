package com.app.userprofile.exceptions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.userprofile.exceptions.userprofile.UserAlreadyExistsException;
import com.app.userprofile.exceptions.userprofile.UserProfileCouldNotBeFound;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String ERROR_KEY_NAME = "error";

	@ExceptionHandler(AuthenticationJWTException.class)
	public ResponseEntity<Map<String, String>> handleAuthenticationJWTException(AuthenticationJWTException ex) {
		Map<String, String> error = new HashMap<>();
		error.put(ERROR_KEY_NAME, "Authentication");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		Map<String, String> error = new LinkedHashMap<>();
		error.put(ERROR_KEY_NAME, "User_Creation");
		error.put("message:", ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		errors.put(ERROR_KEY_NAME, "Missing Parameters");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(UserProfileCouldNotBeFound.class)
	public ResponseEntity<Map<String, String>> handledUserProfileCouldNotBeFound(UserProfileCouldNotBeFound ex) {
		Map<String, String> error = new HashMap<>();
		error.put(ERROR_KEY_NAME, "UserProfile");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
