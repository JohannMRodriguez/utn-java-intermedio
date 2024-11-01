package com.utn.airbnb.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.utn.airbnb.dto.ClientCredentialsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseClientDto {
    private Long id;
    private String name;
    private String gender;
    private Integer edad;
    private ClientCredentialsDto credentials;
}
