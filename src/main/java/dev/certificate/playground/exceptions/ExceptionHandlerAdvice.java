package dev.certificate.playground.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


    //Business errors exception handler
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleAPIException(ResponseStatusException e) {
        ApiExceptionPayload payload = new ApiExceptionPayload(e.getReason());
        return new ResponseEntity<Object>(payload, e.getStatus());
    }


    //Entity Validation exception handler
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, Object> errorsMap = new HashMap<>();
        errors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorsMap.put(fieldName, message);
        });
        ApiExceptionPayload payload = new ApiExceptionPayload("Validation Error", errorsMap);
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }


    //Database exceptions handling
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException dataAccessException) {
        ApiExceptionPayload payload = new ApiExceptionPayload("Internal server error. Please try again later");
        return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
