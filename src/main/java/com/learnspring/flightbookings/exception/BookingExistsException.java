package com.learnspring.flightbookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingExistsException extends RuntimeException{

    public BookingExistsException(String message){
        super(message);
    }
}
