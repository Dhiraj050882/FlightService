package com.learnspring.flightbookings.mapper;

import com.learnspring.flightbookings.dto.BookingInfoDto;
import com.learnspring.flightbookings.entity.Booking_Information;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper(componentModel = "spring")
//@Repository
public interface BookingInfoMapper {
    Booking_Information toBookingEntity(BookingInfoDto bookingInfoDto);
    BookingInfoDto toBookingDto(Booking_Information bookingInfoDto);

    List<Booking_Information> toBookingEntityList(List<BookingInfoDto> bookingInfoDto);
    List<BookingInfoDto> toBookingDtoList(List<Booking_Information> bookingInfoDto);
}
