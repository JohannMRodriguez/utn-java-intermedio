package com.utn.airbnb.services.composite;

import com.utn.airbnb.application.exceptions.BadRequestException;
import com.utn.airbnb.dto.request.RequestBookingByDescriptionDto;
import com.utn.airbnb.dto.response.ResponseBookingDto;
import com.utn.airbnb.dto.response.ResponseRentalDto;
import com.utn.airbnb.services.implementation.ClientService;
import com.utn.airbnb.services.implementation.RentalService;
import com.utn.airbnb.utils.Mensajes;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private RentalService rentalService;
    @Autowired
    private ClientService clientService;

    public ResponseBookingDto bookRentalByDescription(String description, RequestBookingByDescriptionDto request) {

        if (Optional.ofNullable(description).isEmpty()) {
            throw new BadRequestException(Mensajes.CAMPO_DESCRIPTION_ERROR);
        }

        var clientId = clientService.obtenerClientePorUsername(request.getUsername()).getId();

        var rentals = rentalService.obtenerTodosAlquileres();
        var rental = rentals.stream()
                .filter(each -> StringUtils.equalsIgnoreCase(each.getDescription(), description))
                .findFirst()
                .orElse(new ResponseRentalDto());

        if (Optional.ofNullable(rental.getDescription()).isEmpty()) { throw new BadRequestException(Mensajes.ALQUILER_NOT_FOUND); }

        if (Boolean.TRUE.equals(rental.getOnRent())) { throw new BadRequestException(Mensajes.ALQUILER_INDISPONIBLE); }

        var toogleStatusRental = rentalService.cambiarEstado(rental.getId());

        var response = new ResponseBookingDto();
        response.setRentalId(toogleStatusRental.getId());
        response.setClientId(clientId);
        return response;
    }

    public List<>
}
