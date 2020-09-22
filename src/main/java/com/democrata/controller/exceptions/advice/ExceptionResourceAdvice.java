package com.democrata.controller.exceptions.advice;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.democrata.controller.errors.ErrorMessage;
import com.democrata.controller.errors.ErrorMessageBuilder;
import com.democrata.controller.exceptions.EntityNotFoundException;
import com.democrata.controller.exceptions.InternalServerErrorException;

@ControllerAdvice(basePackages = { "com.democrata" })
public class ExceptionResourceAdvice {

	@Autowired
	private ErrorMessageBuilder errorBuilder;

	public ExceptionResourceAdvice() {
	}

	@ResponseBody
	@ExceptionHandler({ EntityNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage exceptionHandler(EntityNotFoundException ex) {
		return errorBuilder.withDeveloperMessage(MessageFormat.format("{0} not found", ex.getParameters()))
				.withUserMessage(
						MessageFormat.format("You attempted to get a {0}, but did not find any", ex.getParameters()))
				.withErrorCode(20023)
				.build();
	}

	@ResponseBody
	@ExceptionHandler({ InternalServerErrorException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage exceptionHandler(InternalServerErrorException ex) {
		return errorBuilder.withDeveloperMessage(MessageFormat.format("Internal server error {0}", ex.getParameters()))
				.withUserMessage(MessageFormat.format(
						"Was encountered an error when processing your request. We apologize for the inconvenience.",
						ex.getParameters()))
				.withErrorCode(10000)
				.build();
	}

	@ResponseBody
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage exceptionHandler(HttpMessageNotReadableException ex) {
		return errorBuilder.withDeveloperMessage(ex.getMessage()).withUserMessage(
				"We had an error while deserializing your submitted object, Check the types of fields in the contract.")
				.withErrorCode(10000)
				.build();
	}
}
