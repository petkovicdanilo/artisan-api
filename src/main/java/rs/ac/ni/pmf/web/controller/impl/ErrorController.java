package rs.ac.ni.pmf.web.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ErrorInfo;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ErrorType;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;

@ControllerAdvice
@ResponseBody
public class ErrorController {

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorInfo handleBadRequestException(final BadRequestException e) {
		return ErrorInfo.builder()
				.errorType(ErrorType.BAD_REQUEST)
				.message(e.getMessage())
				.build();
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorInfo handleNotFoundException(final ResourceNotFoundException e) {
		return ErrorInfo.builder()
				.errorType(ErrorType.NOT_FOUND)
				.resourceType(e.getResourceType())
				.message(e.getMessage())
				.build();
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorInfo handleDuplicateResourceException(final DuplicateResourceException e) {
		return ErrorInfo.builder()
				.errorType(ErrorType.DUPLICATE)
				.resourceType(e.getResourceType())
				.message(e.getMessage())
				.build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorInfo handleValidationExceptions(
	  MethodArgumentNotValidException e) {
	    Map<String, String> errors = new HashMap<>();
		
	    e.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    return ErrorInfo.builder()
				.errorType(ErrorType.VALIDATION_ERROR)
				.errorDetails(errors)
				.resourceType(ResourceType.SENT_RESOURCE)
				.message("Validation error occurred. More info in errorDetails.")
				.build();
	}
}
