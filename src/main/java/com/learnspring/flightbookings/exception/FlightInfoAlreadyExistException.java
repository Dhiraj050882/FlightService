package com.learnspring.flightbookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FlightInfoAlreadyExistException extends RuntimeException{

    public FlightInfoAlreadyExistException(String message){
        super(message);
    }
}
