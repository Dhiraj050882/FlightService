package com.learnspring.flightbookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.learnspring.flightbookings")
public class FlightBookingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightBookingsApplication.class, args);
    }

}
