package com.utn.airbnb.services;

import com.utn.airbnb.dto.request.RequestCategoryDto;
import com.utn.airbnb.dto.request.RequestRentalDto;
import com.utn.airbnb.dto.response.ResponseCategoryDto;
import com.utn.airbnb.dto.response.ResponseRentalDto;

import java.util.List;

public interface IRentalService {
    ResponseRentalDto crearNuevoAlquiler(RequestRentalDto request);
    ResponseRentalDto obtenerAlquilerPorId(Long id);
    List<ResponseRentalDto> obtenerTodosAlquileres();
    void eliminarAlquiler(Long id);
    ResponseRentalDto cambiarEstado(Long id);
    ResponseCategoryDto obtenerCategoriaPorParametros(RequestCategoryDto request);
}
