package com.utn.airbnb.services;

import com.utn.airbnb.dto.ClientCredentialsDto;
import com.utn.airbnb.dto.request.RequestClientDto;
import com.utn.airbnb.dto.response.ResponseClientDto;

public interface IClientService {
    ResponseClientDto obtenerClientePorId(Long id);
    ResponseClientDto obtenerClientePorUsername(String username);
    ClientCredentialsDto obtenerClienteCredentialsPorUsername(String username);
    ResponseClientDto crearCliente(RequestClientDto cliente);
}
