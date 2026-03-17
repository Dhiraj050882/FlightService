package com.learnspring.flightbookings.controller;

import com.learnspring.flightbookings.dto.BookingInfoDto;
import com.learnspring.flightbookings.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-details")
@AllArgsConstructor
public class BookingController {

    BookingService bookingService;

    @PostMapping("/add-Booking")
    public BookingInfoDto addFlightBooking(@RequestBody BookingInfoDto bookingInfo){
        return bookingService.addFlight(bookingInfo);
    }

    @GetMapping("/all-bookings")
    public List<BookingInfoDto> findAllBookings(){
        return bookingService.findAllBookings();
    }

    @GetMapping("/bookings-by-flight")
    public List<BookingInfoDto> findByFlightNumber(@RequestParam String flightNumber){
        return bookingService.findBookingsByFlightNumber(flightNumber);
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
