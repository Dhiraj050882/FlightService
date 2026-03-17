package com.learnspring.flightbookings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "bookingStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking_Information {
    @MongoId
    @NonNull @Indexed(unique = true)
    private String BookingId;
    private String PassengerId;
    @NonNull
    private String flightNumber;
    @NonNull
    private String SeatNumber;
    @NonNull
    private  String seatCategory;

}
