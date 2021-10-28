package com.mc.customer.servicing.exception.v1;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mc.customer.servicing.dto.ResponseErrorDto;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(basePackages = "com.mc.customer.servicing.web.v1")
@Slf4j
public class GlobalExceptionHandler {

	private final MessageSource messageSource;

	@Autowired
	private GlobalExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(value = NotFoundException.class)
	@ResponseBody
	public ResponseEntity<ResponseErrorDto> handleNotFoundException(NotFoundException ne){
		
		ResponseErrorDto responseErrorDto = ResponseErrorDto
											.builder().code(HttpStatus.NOT_FOUND.value())
											.errorMessage(Collections.singletonList(ne.getMessage()))
											.build();
		log.debug("Not found. Respons: {} " , responseErrorDto);
		return new ResponseEntity<>(responseErrorDto, HttpStatus.NOT_FOUND);
	}
	
}
