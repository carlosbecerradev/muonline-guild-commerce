package com.cbh.muonlineguildcommerce.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cbh.muonlineguildcommerce.dto.request.LoginRequest;
import com.cbh.muonlineguildcommerce.dto.response.AuthenticationSuccessfulResponse;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.UserNotFound;
import com.cbh.muonlineguildcommerce.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestControllerAdvice
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	private final AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfulResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
		if (authenticationService.login(loginRequest) == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<AuthenticationSuccessfulResponse>(authenticationService.login(loginRequest),
				HttpStatus.OK);
	}

	@GetMapping("/check-access-token")
	public ResponseEntity<Map<String, Object>> checkAccessToken() {
		return new ResponseEntity<Map<String, Object>>(authenticationService.checkAccessToken(),
				HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream()
				.map(err -> new ApiError(err.getCodes(), err.getDefaultMessage())).collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public List<ApiError> handleValidationExceptions(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(err -> new ApiError(err.getPropertyPath().toString(), err.getMessage()))
				.collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFound.class)
	public List<ApiError> handleNotFoundExceptions(UserNotFound ex) {
		return Collections.singletonList(new ApiError("user.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
