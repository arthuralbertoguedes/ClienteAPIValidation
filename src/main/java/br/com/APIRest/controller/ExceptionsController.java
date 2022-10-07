package br.com.APIRest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.APIRest.Validation.ApiError;

@RestControllerAdvice
public class ExceptionsController {
	
	/*
	 * Exceptions lançadas pelo @Valid
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationErrors(MethodArgumentNotValidException ex) {

		BindingResult br = ex.getBindingResult();
		List<String> erros = br.getAllErrors().stream()
			.map( error -> error.getDefaultMessage() )
			.collect(Collectors.toList());
		
		return new ApiError(400, erros);
	}
	
	/*
	 * Exceptions lançadas pela Api do tipo ResponseStatusException
	 */
	@ExceptionHandler(value = ResponseStatusException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleResponseStatusExceptionErrors(ResponseStatusException ex) {

		return new ApiError(400, ex.getMessage());
	}
	
}
