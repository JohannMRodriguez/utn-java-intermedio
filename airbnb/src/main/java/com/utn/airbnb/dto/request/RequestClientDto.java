package com.utn.airbnb.dto.request;

import com.utn.airbnb.dto.ClientCredentialsDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestClientDto {
    private String name;
    private String gender;
    private Integer edad;
    private ClientCredentialsDto credentials;
}
