package com.utn.airbnb.services.adapter;

import com.utn.airbnb.dto.response.ResponseBookingDetailsDto;
import com.utn.airbnb.entities.Booking;
import com.utn.airbnb.services.implementation.ClientService;
import com.utn.airbnb.services.implementation.RentalService;

public class BookingAdapter {
    private final RentalService rentalService;
    private final ClientService clientService;

    public BookingAdapter(RentalService rentalService, ClientService clientService) {
        this.rentalService = rentalService;
        this.clientService = clientService;
    }

    public ResponseBookingDetailsDto adapt(Booking booking) {
        var rentalDetails = rentalService.obtenerAlquilerPorId(booking.getIdRental());
        var clientDetails = clientService.obtenerClientePorId(booking.getIdClient());

        var responseBooking = new ResponseBookingDetailsDto();
        responseBooking.setClientName(clientDetails.getName());
        responseBooking.setRentalCategory(rentalDetails.getCategory());
        responseBooking.setRentalDescription(rentalDetails.getDescription());

        return responseBooking;
    }
}
