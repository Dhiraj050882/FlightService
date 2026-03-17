package com.learnspring.flightbookings.repository;

import com.learnspring.flightbookings.entity.Booking_Information;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface BookingRepository extends MongoRepository<Booking_Information, String> {
        List<Booking_Information> findByFlightNumber(String fightNumber);
        void deleteBySeatNumber(String seatNumber);
        Optional<Booking_Information> findByPassengerIdAndFlightNumber(String passengerId,String fightNumber);
        void deleteByPassengerIdAndFlightNumber(String passengerId, String flightNumber);
}
