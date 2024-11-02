package com.utn.airbnb.resources;

import com.utn.airbnb.application.swagger.SwaggerApiConfig;
import com.utn.airbnb.dto.request.RequestClientDto;
import com.utn.airbnb.dto.request.RequestRentalDto;
import com.utn.airbnb.dto.response.ResponseClientDto;
import com.utn.airbnb.dto.response.ResponseRentalDto;
import com.utn.airbnb.services.implementation.ClientService;
import com.utn.airbnb.services.implementation.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.utn.airbnb.utils.Constantes.*;

@RestController
@RequestMapping(CLIENT_BASE_PATH)
@Tag(name = SwaggerApiConfig.API_CLIENT_TAG)
public class ClientResource {
    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    @Operation(summary = OBTENER_CLIENTE_TAG)
    public ResponseEntity<ResponseClientDto> getClientById(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.obtenerClientePorId(id));
    }

    @PostMapping
    @Operation(summary = CREAR_CLIENTE_TAG)
    public ResponseEntity<ResponseClientDto> createNewClient(@RequestBody RequestClientDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearCliente(request));
    }
}
