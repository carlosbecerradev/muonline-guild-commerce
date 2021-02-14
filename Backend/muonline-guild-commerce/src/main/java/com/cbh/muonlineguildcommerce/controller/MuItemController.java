package com.cbh.muonlineguildcommerce.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cbh.muonlineguildcommerce.dto.request.MuItemRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemResponse;
import com.cbh.muonlineguildcommerce.dto.response.PageTemplate;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.MuItemNotFound;
import com.cbh.muonlineguildcommerce.mapper.PageTemplateMapper;
import com.cbh.muonlineguildcommerce.model.service.MuItemService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/mu-item")
@AllArgsConstructor
public class MuItemController {

	private final MuItemService muItemService;
	private final PageTemplateMapper<MuItemResponse> pageTemplateMapper;

	@GetMapping
	public ResponseEntity<PageTemplate<MuItemResponse>> finAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Page<MuItemResponse> resultPage = muItemService.findAll(page, size);
		return new ResponseEntity<>(pageTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MuItemResponse> finOneById(@PathVariable @Positive long id) {
		return new ResponseEntity<>(muItemService.findOneById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MuItemResponse> save(@RequestBody @Valid MuItemRequest muItemRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(muItemService.save(muItemRequest));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MuItemResponse> edit(@PathVariable @Positive long id,
			@RequestBody @Valid MuItemRequest muItemRequest) {
		return new ResponseEntity<>(muItemService.edit(muItemRequest, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneById(@PathVariable @Positive long id) {
		muItemService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
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
	@ExceptionHandler(MuItemNotFound.class)
	public List<ApiError> handleNotFoundExceptions(MuItemNotFound ex) {
		return Collections.singletonList(new ApiError("mu-item.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
