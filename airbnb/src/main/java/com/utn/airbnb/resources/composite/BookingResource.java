package com.utn.airbnb.resources.composite;

import com.utn.airbnb.dto.request.RequestBookingByDescriptionDto;
import com.utn.airbnb.dto.response.ResponseBookingDto;
import com.utn.airbnb.services.composite.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.utn.airbnb.utils.Constantes.BOOKING_BASE_PATH;

@RestController
@RequestMapping(BOOKING_BASE_PATH)
public class BookingResource {
    @Autowired
    private BookingService service;

    @PostMapping
    public ResponseEntity<ResponseBookingDto> bookRentalByDescription(
            @RequestParam(name = "description") String description,
            @RequestBody RequestBookingByDescriptionDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.bookRentalByDescription(description, request));
    }
}
