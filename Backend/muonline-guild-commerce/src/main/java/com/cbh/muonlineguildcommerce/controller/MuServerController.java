package com.cbh.muonlineguildcommerce.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cbh.muonlineguildcommerce.dto.request.MuServerRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuServerResponse;
import com.cbh.muonlineguildcommerce.dto.response.PageTemplate;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.MuServerNotFound;
import com.cbh.muonlineguildcommerce.mapper.PageTemplateMapper;
import com.cbh.muonlineguildcommerce.model.service.MuServerService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestControllerAdvice
@RestController
@RequestMapping("/api/mu-servers")
@AllArgsConstructor
public class MuServerController {

	private final MuServerService muServerService;
	private final PageTemplateMapper<MuServerResponse> pageTemplateMapper;

	@GetMapping
	public ResponseEntity<PageTemplate<MuServerResponse>> findAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false) Boolean enabled) {		
		Page<MuServerResponse> resultPage = null;
		
		if(enabled == null) {
			resultPage = muServerService.findAll(page, size);
		} else {
			resultPage = muServerService.findByEnabled(enabled, page, size);
		}
		
		return new ResponseEntity<>(pageTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MuServerResponse> findOneById(@PathVariable @Positive long id) {
		return new ResponseEntity<>(muServerService.findOneById(id), HttpStatus.OK);
	}

	@RolesAllowed(value = { "ROLE_ADMIN" })
	@PostMapping
	public ResponseEntity<MuServerResponse> save(@RequestBody @Valid MuServerRequest muServerRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(muServerService.save(muServerRequest));
	}

	@RolesAllowed(value = { "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<MuServerResponse> edit(@PathVariable @Positive long id,
			@RequestBody @Valid MuServerRequest muServerRequest) {
		return new ResponseEntity<>(muServerService.edit(muServerRequest, id), HttpStatus.OK);
	}

	@Secured(value = { "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneById(@PathVariable @Positive long id) {
		muServerService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<MuServerResponse>> findAll() {
		return new ResponseEntity<>(muServerService.findAll(), HttpStatus.OK);
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
	@ExceptionHandler(MuServerNotFound.class)
	public List<ApiError> handleNotFoundExceptions(MuServerNotFound ex) {
		return Collections.singletonList(new ApiError("mu-server.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
