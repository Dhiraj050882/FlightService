package com.learnspring.flightbookings.service.impl;

import com.learnspring.flightbookings.dto.FlightInfoDto;
import com.learnspring.flightbookings.entity.FlightInformation;
import com.learnspring.flightbookings.exception.FlightInfoAlreadyExistException;
import com.learnspring.flightbookings.exception.ResourceNotFoundException;
import com.learnspring.flightbookings.mapper.FlightInfoMapper;
import com.learnspring.flightbookings.repository.FlightRepository;
import com.learnspring.flightbookings.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Service
@AllArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;


    @Autowired
    FlightInfoMapper mapper;

    private enum FlightCode{
        AI, IX, QP, AK
    }

    @Transactional
    public FlightInformation addFlight(FlightInfoDto information){


        FlightCode[] flightValue = FlightCode.values();
        int flightIndex = ThreadLocalRandom.current().nextInt(flightValue.length);
        String strflightNumber = flightValue[flightIndex] + String.valueOf(new Random().nextInt(900) + 1000);

        information.setFlightNumber(strflightNumber);
        Optional<FlightInformation> checkFlightExists = flightRepository.findByFlightNumberAndDepartureDate(information.getFlightNumber(), information.getDepartureDate());

        if(checkFlightExists.isPresent()){
            throw new FlightInfoAlreadyExistException("Flight Information for the flight "+information.getFlightNumber()+"with departureDate "+information.getDepartureDate()+"already available.");
        }

        FlightInformation flightInfo = mapper.toEntity(information);
        log.info("flight ID for record created: {}", information.getFlightNumber());
        return flightRepository.save(flightInfo);
    }

    public List<FlightInfoDto> findAllFlights(){
        List<FlightInformation> flights = flightRepository.findAll();
        return mapper.toFlightDtoList(flights);
    }

    public List<FlightInformation> findByStatus(String status){
        List<FlightInformation> flightByStatus = flightRepository.findByStatus(status);
        if(flightByStatus.isEmpty()){
            throw new ResourceNotFoundException("FlightService", "Flight By Status", status);
        }
        return flightByStatus;
    }


    public FlightInformation findByFlightNumber(String flightNumber){

        return flightRepository.findById(flightNumber).orElseThrow(
                () -> new ResourceNotFoundException("FlightService", "Flight by Flight Number", flightNumber)
        );
    }

    public FlightInfoDto updateFlightInfo( FlightInfoDto updateInfo){
        FlightInformation existingFlight = flightRepository.findById(updateInfo.getFlightNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Flight Service","Flight Number",updateInfo.getFlightNumber())
        );
        existingFlight.setSourceAirport(updateInfo.getSourceAirport());
        existingFlight.setDestinationAirport(updateInfo.getDestinationAirport());
        existingFlight.setStatus(updateInfo.getStatus());
        FlightInformation savedFlight = flightRepository.save(existingFlight);
        return mapper.toDto(savedFlight);
    }

    public boolean deleteFlightInfo(String flightNumber){
        flightRepository.deleteByFlightNumber(flightNumber);
        log.info("Flight information for Flight Number : {} removed successfully from the system" , flightNumber );
        return true;
    }

}
