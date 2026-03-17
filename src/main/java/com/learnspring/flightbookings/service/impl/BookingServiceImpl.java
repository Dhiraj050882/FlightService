package com.learnspring.flightbookings.service.impl;

import com.learnspring.flightbookings.dto.BookingInfoDto;
import com.learnspring.flightbookings.entity.Booking_Information;
import com.learnspring.flightbookings.entity.FlightInformation;
import com.learnspring.flightbookings.exception.ResourceNotFoundException;
import com.learnspring.flightbookings.mapper.BookingInfoMapper;
import com.learnspring.flightbookings.repository.BookingRepository;
import com.learnspring.flightbookings.repository.FlightRepository;
import com.learnspring.flightbookings.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingInfoMapper bookingMapper;

    @Override
    public BookingInfoDto addFlight(BookingInfoDto information) {

        FlightInformation flightInformation = flightRepository.findByFlightNumber(information.getFlightNumber());
        Booking_Information savedBooking = bookingRepository.save(bookingMapper.toBookingEntity(information));
        flightInformation.getBookingInformation().add(savedBooking);
        flightRepository.save(flightInformation);
        return bookingMapper.toBookingDto(savedBooking);
    }

    @Override
    public List<BookingInfoDto> findAllBookings() {
        List<Booking_Information> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingDtoList(bookings);
    }

    @Override
    public List<BookingInfoDto> findBookingsByFlightNumber(String flightNumber) {
        List<Booking_Information> bookingsByFightNumber = bookingRepository.findByFlightNumber(flightNumber);
        return bookingMapper.toBookingDtoList(bookingsByFightNumber);
    }

    @Override
    public BookingInfoDto updateBooking(BookingInfoDto bookingInfo) {
        Booking_Information bookingEntity = bookingMapper.toBookingEntity(bookingInfo);
        Booking_Information updateBooking = bookingRepository.findById(bookingEntity.getBookingId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Booking Service", "Booking ID", bookingInfo.getBookingId())
                );

        updateBooking.setPassengerId(bookingInfo.getPassengerId());
        updateBooking.setFlightNumber(bookingInfo.getFlightNumber());
        updateBooking.setSeatNumber(bookingEntity.getSeatNumber());
        updateBooking.setSeatCategory(bookingInfo.getSeatCategory());

        Booking_Information savedBooking = bookingRepository.save(updateBooking);
        return bookingMapper.toBookingDto(savedBooking);
    }

    @Override
    public boolean deleteBookingInfo(String seatNumber){
        bookingRepository.deleteBySeatNumber(seatNumber);
        log.info("Flight information for Flight Number : {} removed successfully from the system" , seatNumber );
        return true;
    }

    @Override
    public boolean deleteBookingInfoByPassengerId(String passengerId, String flightNumber) {

        Optional<Booking_Information> byPassengerId = bookingRepository.findByPassengerIdAndFlightNumber(passengerId, flightNumber);

        if(byPassengerId.isPresent()){
            bookingRepository.deleteByPassengerIdAndFlightNumber(passengerId, flightNumber);
            log.info("Flight information for PassengerId : {} removed successfully from the system" , passengerId );
            return true;
        } else {
            throw new ResourceNotFoundException("Booking Service", "Passenger ID", passengerId);
        }
    }


}
