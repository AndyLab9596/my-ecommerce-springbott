package com.andy.my.e_commerce.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    private SignUpAddressRequestDto address;
}
