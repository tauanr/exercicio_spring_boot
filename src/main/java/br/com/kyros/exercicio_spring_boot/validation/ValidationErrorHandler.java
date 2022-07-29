package br.com.kyros.exercicio_spring_boot.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ValidationErrorHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationErrorResponseDto onConstraintValidationException(ConstraintViolationException e) {
		ValidationErrorResponseDto error = new ValidationErrorResponseDto();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
		}
		return error;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationErrorResponseDto onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ValidationErrorResponseDto error = new ValidationErrorResponseDto();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		return error;
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationErrorResponseDto onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		ValidationErrorResponseDto error = new ValidationErrorResponseDto();
		error.getViolations().add(new Violation(e.getName(), e.getMessage()));
		return error;
	}
}
