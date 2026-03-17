package com.learnspring.flightbookings.dto;

import com.learnspring.flightbookings.entity.Booking_Information;
import lombok.Data;

import java.util.List;

@Data
public class FlightInfoDto {

    private String flightNumber;
    private String sourceAirport;
    private String destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private String departureDate;
    private String status;
    private List<Booking_Information> bookingInformation;
}
