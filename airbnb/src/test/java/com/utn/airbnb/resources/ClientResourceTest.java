package com.utn.airbnb.resources;

import com.utn.airbnb.dto.request.RequestClientDto;
import com.utn.airbnb.dto.response.ResponseClientDto;
import com.utn.airbnb.services.implementation.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClientResourceTest {
    @Mock
    private ClientService service;

    @InjectMocks
    private ClientResource controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClientById() {
        Long clientId = 1L;
        ResponseClientDto mockResponse = new ResponseClientDto(); // Set properties as needed
        when(service.obtenerClientePorId(clientId)).thenReturn(mockResponse);

        ResponseEntity<ResponseClientDto> response = controller.getClientById(clientId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testCreateNewClient() {
        RequestClientDto request = new RequestClientDto(); // Set properties as needed
        ResponseClientDto mockResponse = new ResponseClientDto(); // Set properties as needed
        when(service.crearCliente(request)).thenReturn(mockResponse);

        ResponseEntity<ResponseClientDto> response = controller.createNewClient(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }
}
