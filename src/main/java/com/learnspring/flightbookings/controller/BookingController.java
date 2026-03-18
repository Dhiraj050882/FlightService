package com.learnspring.flightbookings.controller;

import com.learnspring.flightbookings.constants.BookingConstant;
import com.learnspring.flightbookings.dto.BookingInfoDto;
import com.learnspring.flightbookings.dto.ResponseDto;
import com.learnspring.flightbookings.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-details")
@AllArgsConstructor
public class BookingController {

    BookingService bookingService;

    @PostMapping("/add-Booking")
    public ResponseEntity<ResponseDto> addFlightBooking(@RequestBody BookingInfoDto bookingInfo){
        bookingService.addFlight(bookingInfo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(BookingConstant.STATUS_201, BookingConstant.MESSAGE_201));
    }

    @GetMapping("/all-bookings")
    public ResponseEntity<List<BookingInfoDto>> findAllBookings(){
        List<BookingInfoDto> bookings = bookingService.findAllBookings();
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    @GetMapping("/bookings-by-flight")
    public ResponseEntity<List<BookingInfoDto>> findByFlightNumber(@RequestParam String flightNumber){
        List<BookingInfoDto> bookingsByFlightNumber = bookingService.findBookingsByFlightNumber(flightNumber);
        return ResponseEntity.status(HttpStatus.OK).body(bookingsByFlightNumber);
    }

    @PutMapping("/bookings")
    public BookingInfoDto updateBookings(@RequestBody BookingInfoDto bookingInfo){
        return bookingService.updateBooking(bookingInfo);
    }

    @DeleteMapping("/flights-by-seat")
    public boolean deleteBySeatNumber(@RequestParam String seatNumber){
        return bookingService.deleteBookingInfo(seatNumber);
    }

    @DeleteMapping("/flights-by-passenger")
    public boolean deleteByPassengerId(@RequestParam String passengerId, @RequestParam String flightNumber){
        return bookingService.deleteBookingInfoByPassengerId(passengerId, flightNumber);
    }

}
