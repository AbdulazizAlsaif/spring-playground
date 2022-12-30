package dev.certificate.playground.exceptions;


import lombok.*;

import java.util.Date;


@Data
public class ApiExceptionPayload {

    private String message;
    private Object body;
    private boolean exception;
    private Date timestamp;


    public ApiExceptionPayload(String message) {
        this.message = message;
        this.exception=true;
        this.timestamp=new Date();
    }
    public ApiExceptionPayload(String message,  Object body) {
        this.message = message;
        this.body = body;
        this.exception=true;
        this.timestamp=new Date();
    }
}