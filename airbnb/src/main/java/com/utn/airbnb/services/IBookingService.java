package com.utn.airbnb.services;

import com.utn.airbnb.dto.request.RequestBookingDto;
import com.utn.airbnb.dto.response.ResponseBookingDetailsDto;
import com.utn.airbnb.dto.response.ResponseBookingDto;
import com.utn.airbnb.entities.Booking;

import java.util.List;

public interface IBookingService {
    ResponseBookingDto createBooking(RequestBookingDto request);
    List<ResponseBookingDetailsDto> getAllBookings();
}
