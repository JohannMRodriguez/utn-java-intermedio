package com.utn.airbnb.resources.composite;

import com.utn.airbnb.application.swagger.SwaggerApiConfig;
import com.utn.airbnb.dto.request.RequestBookingDto;
import com.utn.airbnb.dto.response.ResponseBookingDetailsDto;
import com.utn.airbnb.dto.response.ResponseBookingDto;
import com.utn.airbnb.services.composite.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.airbnb.utils.Constantes.*;

@RestController
@RequestMapping(BOOKING_BASE_PATH)
@Tag(name = SwaggerApiConfig.API_BOOKING_TAG)
public class BookingResource {
    @Autowired
    private BookingService service;


    @GetMapping
    @Operation(summary = OBTENER_RESERVAS_TAG)
    public ResponseEntity<List<ResponseBookingDetailsDto>> getAllBookings() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllBookings());
    }

    @PostMapping
    @Operation(summary = CREAR_RESERVA_TAG)
    public ResponseEntity<ResponseBookingDto> createBooking(@RequestBody RequestBookingDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createBooking(request));
    }

    @PostMapping("/cancelar")
    @Operation(summary = ELIMINAR_RESERVA_TAG)
    public ResponseEntity<Void> deleteBooking(@RequestBody RequestBookingDto request) {

        service.deleteBooking(request);
        return ResponseEntity.noContent().build();
    }
}
