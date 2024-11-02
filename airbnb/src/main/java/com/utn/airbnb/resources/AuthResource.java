package com.utn.airbnb.resources;

import com.utn.airbnb.application.swagger.SwaggerApiConfig;
import com.utn.airbnb.dto.request.RequestAuthDto;
import com.utn.airbnb.dto.response.ResponseAuthDto;
import com.utn.airbnb.services.implementation.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.utn.airbnb.utils.Constantes.AUTH_BASE_PATH;
import static com.utn.airbnb.utils.Constantes.AUTH_TAG;

@RestController
@RequestMapping(AUTH_BASE_PATH)
@Tag(name = SwaggerApiConfig.API_AUTHORIZATION_TAG)
public class AuthResource {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    @Operation(summary = AUTH_TAG)
    public ResponseEntity<ResponseAuthDto> autenticacion(@RequestBody RequestAuthDto request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.authenticate(request));
    }
}
