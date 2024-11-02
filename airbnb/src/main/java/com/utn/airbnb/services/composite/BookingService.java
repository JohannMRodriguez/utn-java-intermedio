package com.utn.airbnb.services.composite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.airbnb.application.exceptions.BadRequestException;
import com.utn.airbnb.dto.request.RequestBookingDto;
import com.utn.airbnb.dto.response.ResponseBookingDetailsDto;
import com.utn.airbnb.dto.response.ResponseBookingDto;
import com.utn.airbnb.entities.Booking;
import com.utn.airbnb.repositories.BookingRepository;
import com.utn.airbnb.services.IBookingService;
import com.utn.airbnb.services.implementation.ClientService;
import com.utn.airbnb.services.implementation.RentalService;
import com.utn.airbnb.utils.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private RentalService rentalService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public ResponseBookingDto createBooking(RequestBookingDto request) {

        if (rentalService.obtenerAlquilerPorId(request.getIdRental()).getOnRent()) {
            throw new BadRequestException(Mensajes.ALQUILER_INDISPONIBLE);
        }

        clientService.obtenerClientePorId(request.getIdClient());

        var booking = bookingRepository.save(objectMapper.convertValue(request, Booking.class));
        rentalService.cambiarEstado(request.getIdRental());
        return objectMapper.convertValue(booking, ResponseBookingDto.class);
    }

    public List<ResponseBookingDetailsDto> getAllBookings() {

        var bookings = bookingRepository.findAll();
        var response = new ArrayList<ResponseBookingDetailsDto>();

        bookings.forEach(each -> {
                    var rentalDetails = rentalService.obtenerAlquilerPorId(each.getIdRental());
                    var clientDetails = clientService.obtenerClientePorId(each.getIdClient());

                    var booking = new ResponseBookingDetailsDto();
                    booking.setClientName(clientDetails.getName());
                    booking.setRentalCategory(rentalDetails.getCategory());
                    booking.setRentalDescription(rentalDetails.getDescription());

                    response.add(booking);
                });

        return response;
    }

    public void deleteBooking(RequestBookingDto request) {

        var booking = bookingRepository.findByIdRental(request.getIdRental());

        if (!Objects.equals(booking.getIdClient(), request.getIdClient())) {
            throw new BadRequestException(Mensajes.INCOMPATIBLE_CLIENT_RENTAL);
        }

        bookingRepository.deleteById(booking.getId());
        rentalService.cambiarEstado(request.getIdRental());
    }
}
