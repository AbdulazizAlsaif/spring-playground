package dev.certificate.playground.config;



import lombok.*;
import java.util.Date;

@Data
public class ApiSuccessPayLoad {

    private String message;
    private Object body;
    private Date timestamp;
    private boolean exception;

    public ApiSuccessPayLoad(Object body) {
        this.message="Success";
        this.body = body;
        this.timestamp= new Date();
        this.exception=false;

    }
    public ApiSuccessPayLoad(String message,Object body) {
        this.message=message;
        this.body = body;
        this.timestamp= new Date();
        this.exception=false;

    }
}
