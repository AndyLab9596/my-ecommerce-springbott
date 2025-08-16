package com.andy.my.e_commerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAddressResponseDto {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
