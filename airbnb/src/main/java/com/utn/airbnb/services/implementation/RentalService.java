package com.utn.airbnb.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.airbnb.application.exceptions.NotFoundException;
import com.utn.airbnb.dto.request.RequestCategoryDto;
import com.utn.airbnb.dto.request.RequestRentalDto;
import com.utn.airbnb.dto.response.ResponseCategoryDto;
import com.utn.airbnb.dto.response.ResponseRentalDto;
import com.utn.airbnb.entities.Rental;
import com.utn.airbnb.entities.RentalCategory;
import com.utn.airbnb.repositories.RentalCategoryRepository;
import com.utn.airbnb.repositories.RentalRepository;
import com.utn.airbnb.services.IRentalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.utn.airbnb.utils.Mensajes.ALQUILER_NOT_FOUND;

@Service
public class RentalService implements IRentalService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalCategoryRepository rentalCategoryRepository;

    @Override
    public ResponseRentalDto crearNuevoAlquiler(RequestRentalDto request) {

        var requestedCategory = new RequestCategoryDto(request.getCategory().getCalification(), request.getCategory().getType());

        var categorySearch = obtenerCategoriaPorParametros(requestedCategory);
        RentalCategory category;
        if (Optional.ofNullable(categorySearch.getId()).isEmpty()) {
            category = rentalCategoryRepository.save(objectMapper.convertValue(requestedCategory, RentalCategory.class));
        }
        else {
            category = objectMapper.convertValue(categorySearch, RentalCategory.class);
        }

        var rental = objectMapper.convertValue(request, Rental.class);
        rental.setCategory(category);

        var rentalCreated = rentalRepository.save(rental);
        return objectMapper.convertValue(rentalCreated, ResponseRentalDto.class);
    }

    @Override
    public ResponseRentalDto obtenerAlquilerPorId(Long id) {

        var rental = rentalRepository.findById(id);

        if (rental.isPresent()) {
            return objectMapper.convertValue(rental.get(), ResponseRentalDto.class);
        }
        throw new NotFoundException(ALQUILER_NOT_FOUND);
    }

    @Override
    public List<ResponseRentalDto> obtenerTodosAlquileres() {

        var rentals = rentalRepository.findAll();

        return rentals.stream()
                .map(rental -> objectMapper.convertValue(rental, ResponseRentalDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarAlquiler(Long id) {

        obtenerAlquilerPorId(id);
        rentalRepository.deleteById(id);
    }

    @Override
    public ResponseRentalDto cambiarEstado(Long id) {

        var rental = rentalRepository.findById(id);

        if (rental.isEmpty()) { throw new NotFoundException(ALQUILER_NOT_FOUND); }

        var updatedRental = rental.get();
        updatedRental.setOnRent(!updatedRental.getOnRent());

        return objectMapper.convertValue(rentalRepository.save(updatedRental), ResponseRentalDto.class);
    }

    @Override
    public ResponseCategoryDto obtenerCategoriaPorParametros(RequestCategoryDto request) {

        var listOfRentsCalificated = rentalCategoryRepository.findByCalification(request.getCalification());

        var rent = listOfRentsCalificated.stream()
                .filter(each -> StringUtils.equalsIgnoreCase(each.getType(), request.getType()))
                .findFirst()
                .orElse(new RentalCategory());

        return objectMapper.convertValue(rent, ResponseCategoryDto.class);
    }
}
