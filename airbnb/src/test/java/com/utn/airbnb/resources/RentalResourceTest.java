package com.utn.airbnb.resources;

import com.utn.airbnb.dto.request.RequestRentalDto;
import com.utn.airbnb.dto.response.ResponseRentalDto;
import com.utn.airbnb.services.implementation.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RentalResourceTest {
    @Mock
    private RentalService service;

    @InjectMocks
    private RentalResource controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRentalById() {
        Long rentalId = 1L;
        ResponseRentalDto mockResponse = new ResponseRentalDto();

        when(service.obtenerAlquilerPorId(rentalId)).thenReturn(mockResponse);

        ResponseEntity<ResponseRentalDto> response = controller.getRentalById(rentalId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testGetAllRentals() {
        List<ResponseRentalDto> mockRentals = Arrays.asList(new ResponseRentalDto(), new ResponseRentalDto());
        when(service.obtenerTodosAlquileres()).thenReturn(mockRentals);

        ResponseEntity<List<ResponseRentalDto>> response = controller.getAllRentals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRentals, response.getBody());
    }

    @Test
    void testCreateNewRental() {
        RequestRentalDto request = new RequestRentalDto(); // Set properties as needed
        ResponseRentalDto mockResponse = new ResponseRentalDto(); // Set properties as needed
        when(service.crearNuevoAlquiler(request)).thenReturn(mockResponse);

        ResponseEntity<ResponseRentalDto> response = controller.createNewRental(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testDeleteRental() {
        Long rentalId = 1L;

        ResponseEntity<Void> response = controller.deleteRental(rentalId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testToggleOnRentState() {
        Long rentalId = 1L;
        ResponseRentalDto mockResponse = new ResponseRentalDto(); // Set properties as needed
        when(service.cambiarEstado(rentalId)).thenReturn(mockResponse);

        ResponseEntity<ResponseRentalDto> response = controller.toogleOnRentState(rentalId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }
}
