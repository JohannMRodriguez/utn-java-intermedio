package com.utn.airbnb.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.airbnb.application.exceptions.BadRequestException;
import com.utn.airbnb.application.exceptions.NotFoundException;
import com.utn.airbnb.dto.ClientCredentialsDto;
import com.utn.airbnb.dto.request.RequestClientDto;
import com.utn.airbnb.dto.response.ResponseClientDto;
import com.utn.airbnb.entities.Client;
import com.utn.airbnb.entities.ClientCredentials;
import com.utn.airbnb.repositories.ClientCredentialsRepository;
import com.utn.airbnb.repositories.ClientRepository;
import com.utn.airbnb.services.IClientService;
import com.utn.airbnb.utils.Mensajes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private ClientCredentialsRepository credentialsRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseClientDto obtenerClientePorId(Long id) {

        var client = repository.findById(id);

        if (client.isEmpty()) { throw new NotFoundException(Mensajes.CLIENT_NOT_FOUND); }

        return objectMapper.convertValue(client.get(), ResponseClientDto.class);
    }

    @Override
    public ResponseClientDto obtenerClientePorUsername(String username) {

        var clients = repository.findAll();

        var client = clients.stream()
                .filter(each -> StringUtils.equals(each.getCredentials().getUsername(), username))
                .findFirst()
                .orElse(new Client());
        System.out.println(clients);

        if (Optional.ofNullable(client.getId()).isEmpty()) { throw new NotFoundException(Mensajes.CLIENT_NOT_FOUND); }

        return objectMapper.convertValue(client, ResponseClientDto.class);
    }

    @Override
    public ClientCredentialsDto obtenerClienteCredentialsPorUsername(String username) {

        var client = credentialsRepository.findByUsername(username);

        if (client.isEmpty()) { return new ClientCredentialsDto(); }

        return objectMapper.convertValue(client.get(), ClientCredentialsDto.class);
    }

    @Override
    public ResponseClientDto crearCliente(RequestClientDto client) {

//        TODO validar q el username ya no este creado
        var user = Optional.ofNullable(obtenerClienteCredentialsPorUsername(client.getCredentials().getUsername()).getUsername());
        if (user.isPresent()) { throw new BadRequestException(Mensajes.CLIENT_EXISTENTE); }

//        TODO encriptar la contrasena
        var credentials = objectMapper.convertValue(client.getCredentials(), ClientCredentials.class);
        var clientCredentials = credentialsRepository.save(credentials);

        var clientRequest = objectMapper.convertValue(client, Client.class);
        clientRequest.setCredentials(clientCredentials);
        var clientSaved = repository.save(clientRequest);

        return objectMapper.convertValue(clientSaved, ResponseClientDto.class);
    }
}
