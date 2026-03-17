package com.learnspring.flightbookings.service;

import com.learnspring.flightbookings.dto.BookingInfoDto;
import com.learnspring.flightbookings.entity.Booking_Information;

import java.util.List;

public interface BookingService {
    BookingInfoDto addFlight(BookingInfoDto information);

    List<BookingInfoDto> findAllBookings();

    List<BookingInfoDto> findBookingsByFlightNumber(String flightNumber);

    BookingInfoDto updateBooking(BookingInfoDto bookingInfo);

    boolean deleteBookingInfo(String seatNumber);

    boolean deleteBookingInfoByPassengerId(String passengerId, String flightNumber);
}
