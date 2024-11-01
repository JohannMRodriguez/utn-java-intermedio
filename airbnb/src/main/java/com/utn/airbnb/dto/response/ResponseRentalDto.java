package com.utn.airbnb.dto.response;

import com.utn.airbnb.dto.RentalCategoryDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseRentalDto {
    private Long id;
    private String description;
    private RentalCategoryDto category;
    private Boolean onRent;
    private Float pricePerDay;
}
