package com.utn.airbnb.application.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApiConfig {

    public static final String API_MAIN_TAG = "AirBnb Project";
    public static final String API_AUTHORIZATION_TAG = "Servicio de autorizacion con token";
    public static final String API_CLIENT_TAG = "Servicio de clientes/usuarios";
    public static final String API_RENTAL_TAG = "Servicio de alquileres/rentas";
    public static final String API_BOOKING_TAG = "Servicio de reservas por clientes/alquileres";
    public static final String API_DESCRIPTION = "Swagger Ui para todos los endpoints del servicio de airbnb";


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(API_MAIN_TAG)
                        .description(API_DESCRIPTION)
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                                        ;
    }

}
