package com.utn.airbnb.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

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
import com.utn.airbnb.services.implementation.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @Mock
    private ClientCredentialsRepository credentialsRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerClientePorId() {
        Long id = 1L;
        Client client = new Client();
        ResponseClientDto expectedResponse = new ResponseClientDto();

        when(repository.findById(id)).thenReturn(Optional.of(client));
        when(objectMapper.convertValue(client, ResponseClientDto.class)).thenReturn(expectedResponse);

        ResponseClientDto response = clientService.obtenerClientePorId(id);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testObtenerClientePorId_NotFound() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clientService.obtenerClientePorId(id));
    }

    @Test
    void testObtenerClientePorUsername() {
        String username = "user123";
        Client client = new Client();
        client.setId(1L);
        client.setCredentials(new ClientCredentials());
        client.getCredentials().setUsername(username);

        List<Client> clients = List.of(client);
        ResponseClientDto expectedResponse = new ResponseClientDto();

        when(repository.findAll()).thenReturn(clients);
        when(objectMapper.convertValue(client, ResponseClientDto.class)).thenReturn(expectedResponse);

        ResponseClientDto response = clientService.obtenerClientePorUsername(username);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testObtenerClientePorUsername_NotFound() {
        String username = "nonexistent";

        when(repository.findAll()).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> clientService.obtenerClientePorUsername(username));
    }

    @Test
    void testObtenerClienteCredentialsPorUsername() {
        String username = "user123";
        ClientCredentials credentials = new ClientCredentials();
        credentials.setUsername(username);
        ClientCredentialsDto expectedResponse = new ClientCredentialsDto();

        when(credentialsRepository.findByUsername(username)).thenReturn(Optional.of(credentials));
        when(objectMapper.convertValue(credentials, ClientCredentialsDto.class)).thenReturn(expectedResponse);

        ClientCredentialsDto response = clientService.obtenerClienteCredentialsPorUsername(username);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testObtenerClienteCredentialsPorUsername_NotFound() {
        String username = "nonexistent";

        when(credentialsRepository.findByUsername(username)).thenReturn(Optional.empty());

        ClientCredentialsDto response = clientService.obtenerClienteCredentialsPorUsername(username);

        assertEquals(new ClientCredentialsDto(), response);
    }
}

