package com.learnspring.flightbookings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "flightStatus")
@Data @AllArgsConstructor @NoArgsConstructor
public class FlightInformation {

    @MongoId
    @Indexed(unique = true)
    @NonNull
    private String flightNumber;
    @NonNull
    private String sourceAirport;
    @NonNull
    private String destinationAirport;
    @NonNull
    private String departureTime;
    @NonNull
    private String arrivalTime;
    @NonNull
    private String departureDate;
    @NonNull
    private String status;

    @DBRef
    private List<Booking_Information> bookingInformation=new ArrayList<>();
}
