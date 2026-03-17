package com.learnspring.flightbookings.dto;

import lombok.Data;

@Data
public class BookingInfoDto {
    private String BookingId;
    private String flightNumber;
    private String seatNumber;
    private  String seatCategory;
    private String passengerId;
}
