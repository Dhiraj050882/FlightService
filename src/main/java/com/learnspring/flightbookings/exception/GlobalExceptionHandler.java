package com.learnspring.flightbookings.exception;

import com.learnspring.flightbookings.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FlightInfoAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleFlightAlreadyExistsException(FlightInfoAlreadyExistException exception, WebRequest request){
        ErrorResponseDto errResponse = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleBookingExistsException(BookingExistsException exception, WebRequest request){
        ErrorResponseDto errResponse = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(
                errorResponseDto,
                HttpStatus.NOT_FOUND
        );
    }

}
