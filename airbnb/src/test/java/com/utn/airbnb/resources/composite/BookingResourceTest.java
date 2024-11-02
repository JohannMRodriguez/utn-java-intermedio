package com.utn.airbnb.resources.composite;

import com.utn.airbnb.dto.request.RequestBookingDto;
import com.utn.airbnb.dto.response.ResponseBookingDetailsDto;
import com.utn.airbnb.dto.response.ResponseBookingDto;
import com.utn.airbnb.services.composite.BookingService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BookingResourceTest {

    @Mock
    private BookingService service;

    @InjectMocks
    private BookingResource controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBookings() {
        List<ResponseBookingDetailsDto> mockBookings = Arrays.asList(new ResponseBookingDetailsDto(), new ResponseBookingDetailsDto());
        when(service.getAllBookings()).thenReturn(mockBookings);

        ResponseEntity<List<ResponseBookingDetailsDto>> response = controller.getAllBookings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBookings, response.getBody());
    }

    @Test
    void testCreateBooking() {
        RequestBookingDto request = new RequestBookingDto(); // Set properties as needed
        ResponseBookingDto mockResponse = new ResponseBookingDto(); // Set properties as needed
        when(service.createBooking(request)).thenReturn(mockResponse);

        ResponseEntity<ResponseBookingDto> response = controller.createBooking(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testDeleteBooking() {
        // Arrange
        RequestBookingDto request = new RequestBookingDto(); // Set properties as needed
        doNothing().when(service).deleteBooking(request);

        // Act
        ResponseEntity<Void> response = controller.deleteBooking(request);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
