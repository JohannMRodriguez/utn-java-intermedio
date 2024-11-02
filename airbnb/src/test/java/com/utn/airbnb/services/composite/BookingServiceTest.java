package com.utn.airbnb.services.composite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.airbnb.application.exceptions.BadRequestException;
import com.utn.airbnb.dto.request.RequestBookingDto;
import com.utn.airbnb.entities.Booking;
import com.utn.airbnb.repositories.BookingRepository;
import com.utn.airbnb.services.implementation.ClientService;
import com.utn.airbnb.services.implementation.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookingServiceTest {

    @Mock
    private RentalService rentalService;

    @Mock
    private ClientService clientService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeleteBooking() {
        RequestBookingDto request = new RequestBookingDto();
        request.setIdRental(1L);
        request.setIdClient(1L);

        Booking booking = new Booking();
        booking.setId(1L);
        booking.setIdClient(1L);
        when(bookingRepository.findByIdRental(request.getIdRental())).thenReturn(booking);

        bookingService.deleteBooking(request);

        verify(bookingRepository).deleteById(booking.getId());
        verify(rentalService).cambiarEstado(request.getIdRental());
    }

    @Test
    void testDeleteBooking_IncompatibleClient() {
        RequestBookingDto request = new RequestBookingDto();
        request.setIdRental(1L);
        request.setIdClient(2L);

        Booking booking = new Booking();
        booking.setId(1L);
        booking.setIdClient(1L);
        when(bookingRepository.findByIdRental(request.getIdRental())).thenReturn(booking);

        assertThrows(BadRequestException.class, () -> bookingService.deleteBooking(request));
    }
}

