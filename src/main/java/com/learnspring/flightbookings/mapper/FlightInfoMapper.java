package com.learnspring.flightbookings.mapper;

import com.learnspring.flightbookings.dto.FlightInfoDto;
import com.learnspring.flightbookings.entity.FlightInformation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightInfoMapper {

        FlightInformation toEntity(FlightInfoDto flightInfoDto);

        FlightInfoDto toDto(FlightInformation information);

        List<FlightInfoDto>  toFlightDtoList(List<FlightInformation> flightDtoList);

        List<FlightInformation> toEntityList(List<FlightInfoDto> flightInfoDto);
}
