package com.utn.airbnb.resources.composite;

import com.utn.airbnb.dto.request.RequestBookingDto;
import com.utn.airbnb.dto.response.ResponseBookingDetailsDto;
import com.utn.airbnb.dto.response.ResponseBookingDto;
import com.utn.airbnb.services.composite.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.airbnb.utils.Constantes.BOOKING_BASE_PATH;

@RestController
@RequestMapping(BOOKING_BASE_PATH)
public class BookingResource {
    @Autowired
    private BookingService service;


    @GetMapping
    public ResponseEntity<List<ResponseBookingDetailsDto>> getAllBookings() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllBookings());
    }

    @PostMapping
    public ResponseEntity<ResponseBookingDto> createBooking(@RequestBody RequestBookingDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createBooking(request));
    }

    @PostMapping("/cancelar")
    public ResponseEntity<Void> deleteBooking(@RequestBody RequestBookingDto request) {

        service.deleteBooking(request);
        return ResponseEntity.noContent().build();
    }
}
