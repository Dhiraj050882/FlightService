package com.learnspring.flightbookings.service;

import com.learnspring.flightbookings.dto.FlightInfoDto;
import com.learnspring.flightbookings.entity.FlightInformation;

import java.util.List;

public interface FlightService {

    FlightInformation addFlight(FlightInfoDto information);
    List<FlightInfoDto> findAllFlights();
    List<FlightInformation> findByStatus(String status);
    FlightInformation findByFlightNumber(String flightNumber);
    FlightInfoDto updateFlightInfo(FlightInfoDto updateInfo);
    boolean deleteFlightInfo(String flightNumber);
}
