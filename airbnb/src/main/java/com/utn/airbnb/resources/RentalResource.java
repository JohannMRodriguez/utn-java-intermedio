package com.utn.airbnb.resources;

import com.utn.airbnb.dto.request.RequestRentalDto;
import com.utn.airbnb.dto.response.ResponseRentalDto;
import com.utn.airbnb.services.implementation.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.airbnb.utils.Constantes.RENTAL_BASE_PATH;

@RestController
@RequestMapping(RENTAL_BASE_PATH)
public class RentalResource {
    @Autowired
    private RentalService service;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRentalDto> getRentalById(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.obtenerAlquilerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseRentalDto>> getAllRentals() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.obtenerTodosAlquileres());
    }

    @PostMapping
    public ResponseEntity<ResponseRentalDto> createNewRental(@RequestBody RequestRentalDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearNuevoAlquiler(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {

        return ResponseEntity
                .noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseRentalDto> toogleOnRentState(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.cambiarEstado(id));
    }
}
