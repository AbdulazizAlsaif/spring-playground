package dev.certificate.playground.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {


    //Business errors exception handler
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleAPIException(ResponseStatusException e) {
        ApiExceptionPayload payload = new ApiExceptionPayload(e.getReason());
        return new ResponseEntity<Object>(payload, e.getStatus());
    }


    //Entity Validation exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors=ex.getBindingResult().getAllErrors();
        Map<String, Object> errorsMap = new HashMap<>();
        errors.forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorsMap.put(fieldName, message);
        });
        ApiExceptionPayload payload = new ApiExceptionPayload("Validation Error",errorsMap);
        return new ResponseEntity<Object>(payload, HttpStatus.BAD_REQUEST);
    }


    //Database exceptions handling
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException e) {
        ApiExceptionPayload payload = new ApiExceptionPayload(e.getMessage());
        return new ResponseEntity<Object>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
