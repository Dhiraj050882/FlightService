package com.learnspring.flightbookings.repository;

import com.learnspring.flightbookings.entity.FlightInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface FlightRepository  extends MongoRepository<FlightInformation, String> {
    List<FlightInformation> findByStatus(String status);
    FlightInformation findByFlightNumber(String flightNumber);
    Optional<FlightInformation> findByFlightNumberAndDepartureDate(String flightNumber, String departureDate);

    void deleteByFlightNumber(String flightNumber);
}
