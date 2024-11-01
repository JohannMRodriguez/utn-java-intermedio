package com.utn.airbnb.services;

import com.utn.airbnb.dto.ClientCredentialsDto;
import com.utn.airbnb.dto.request.RequestClientDto;
import com.utn.airbnb.dto.response.ResponseClientDto;

public interface IClientService {
    ResponseClientDto obtenerClientePorId(Long id);
    ClientCredentialsDto obtenerClientePorUsername(String username);
    ResponseClientDto crearCliente(RequestClientDto cliente);
}
