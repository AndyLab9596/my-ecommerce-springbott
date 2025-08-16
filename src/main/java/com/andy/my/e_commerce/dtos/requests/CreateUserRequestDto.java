package com.andy.my.e_commerce.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDto {
    @NotBlank(message = "User's name is required")
    private String name;

    @NotBlank(message = "User's email is required")
    private String email;

    @NotBlank(message = "User's password is required")
    private String password;

    private String phoneNumber;

    private CreateAddressRequestDto address;
}
