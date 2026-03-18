package com.learnspring.flightbookings.controller;

import com.learnspring.flightbookings.constants.FlightConstant;
import com.learnspring.flightbookings.dto.FlightInfoDto;
import com.learnspring.flightbookings.dto.ResponseDto;
import com.learnspring.flightbookings.entity.FlightInformation;
import com.learnspring.flightbookings.service.FlightService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<List<FlightInfoDto>> getAllFlights(){
        List<FlightInfoDto> allFlights = service.findAllFlights();
        return ResponseEntity.status(HttpStatus.OK).body(allFlights);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FlightInformation>> flightsByStatus(@PathVariable String status){
        List<FlightInformation> byStatus = service.findByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(byStatus);
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<FlightInformation> findByFlightNumber(@PathVariable String flightNumber){
        FlightInformation byFlightNumber = service.findByFlightNumber(flightNumber);
        return ResponseEntity.status(HttpStatus.OK).body(byFlightNumber);
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
