package com.utn.airbnb.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.airbnb.application.exceptions.NotFoundException;
import com.utn.airbnb.dto.RentalCategoryDto;
import com.utn.airbnb.dto.request.RequestCategoryDto;
import com.utn.airbnb.dto.request.RequestRentalDto;
import com.utn.airbnb.dto.response.ResponseCategoryDto;
import com.utn.airbnb.dto.response.ResponseRentalDto;
import com.utn.airbnb.entities.Rental;
import com.utn.airbnb.entities.RentalCategory;
import com.utn.airbnb.repositories.RentalCategoryRepository;
import com.utn.airbnb.repositories.RentalRepository;
import com.utn.airbnb.services.implementation.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RentalServiceTest {
    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private RentalCategoryRepository rentalCategoryRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testObtenerAlquilerPorId() {
        Long id = 1L;
        Rental rental = new Rental();
        ResponseRentalDto expectedResponse = new ResponseRentalDto();

        when(rentalRepository.findById(id)).thenReturn(Optional.of(rental));
        when(objectMapper.convertValue(rental, ResponseRentalDto.class)).thenReturn(expectedResponse);

        ResponseRentalDto response = rentalService.obtenerAlquilerPorId(id);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testObtenerAlquilerPorId_NotFound() {
        Long id = 1L;

        when(rentalRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rentalService.obtenerAlquilerPorId(id));
    }

    @Test
    void testObtenerTodosAlquileres() {
        Rental rental1 = new Rental();
        Rental rental2 = new Rental();
        List<Rental> rentals = Stream.of(rental1, rental2).collect(Collectors.toList());

        ResponseRentalDto responseDto1 = new ResponseRentalDto();
        ResponseRentalDto responseDto2 = new ResponseRentalDto();

        when(rentalRepository.findAll()).thenReturn(rentals);
        when(objectMapper.convertValue(rental1, ResponseRentalDto.class)).thenReturn(responseDto1);
        when(objectMapper.convertValue(rental2, ResponseRentalDto.class)).thenReturn(responseDto2);

        List<ResponseRentalDto> response = rentalService.obtenerTodosAlquileres();

        assertEquals(List.of(responseDto1, responseDto2), response);
    }

    @Test
    void testEliminarAlquiler() {
        Long id = 1L;

        when(rentalRepository.findById(id)).thenReturn(Optional.of(new Rental()));

        rentalService.eliminarAlquiler(id);

        verify(rentalRepository).deleteById(id);
    }

    @Test
    void testCambiarEstado() {
        Long id = 1L;
        Rental rental = new Rental();
        rental.setOnRent(false);

        when(rentalRepository.findById(id)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(rental)).thenReturn(rental);

        rental.setOnRent(true);
        ResponseRentalDto expectedResponse = new ResponseRentalDto();
        when(objectMapper.convertValue(rental, ResponseRentalDto.class)).thenReturn(expectedResponse);

        ResponseRentalDto response = rentalService.cambiarEstado(id);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testObtenerCategoriaPorParametros() {
        RequestCategoryDto request = new RequestCategoryDto(1, "type");
        RentalCategory rentalCategory = new RentalCategory();
        ResponseCategoryDto expectedResponse = new ResponseCategoryDto();

        when(rentalCategoryRepository.findByCalification(request.getCalification())).thenReturn(List.of(rentalCategory));
        when(objectMapper.convertValue(rentalCategory, ResponseCategoryDto.class)).thenReturn(expectedResponse);

        ResponseCategoryDto response = rentalService.obtenerCategoriaPorParametros(request);

        assertEquals(expectedResponse, response);
    }
}
