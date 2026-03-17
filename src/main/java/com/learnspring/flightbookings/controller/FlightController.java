package com.learnspring.flightbookings.controller;

import com.learnspring.flightbookings.constants.FlightConstant;
import com.learnspring.flightbookings.dto.FlightInfoDto;
import com.learnspring.flightbookings.dto.ResponseDto;
import com.learnspring.flightbookings.entity.FlightInformation;
import com.learnspring.flightbookings.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class FlightController {

    FlightService service;

    @PostMapping(path="/add-flight", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDto> addFlight(@RequestBody FlightInfoDto flightInformation){
        service.addFlight(flightInformation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(FlightConstant.STATUS_201,FlightConstant.MESSAGE_201));
    }

    @GetMapping("/")
    public List<FlightInfoDto> getAllFlights(){
        return service.findAllFlights();
    }

    @GetMapping("/status/{status}")
    public List<FlightInformation> flightsByStatus(@PathVariable String status){
        return service.findByStatus(status);
    }

    @GetMapping("/{flightNumber}")
    public FlightInformation findByFlightNumber(@PathVariable String flightNumber){
        return service.findByFlightNumber(flightNumber);
    }

    @PutMapping("/modify-info")
    public FlightInfoDto modifyFlightInfo(@RequestBody FlightInfoDto information){
        return service.updateFlightInfo(information);
    }

    @DeleteMapping("/{flightNumber}")
    public boolean deleteFlightInfo(@PathVariable String flightNumber){
        service.deleteFlightInfo(flightNumber);
        return true;
    }

}
