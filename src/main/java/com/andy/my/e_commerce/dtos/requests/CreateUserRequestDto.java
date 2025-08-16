package com.andy.my.e_commerce.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Email
    private String email;

    @NotBlank(message = "User's password is required")
    @Size(min = 3, max = 5, message = "PASSWORD_INVALID")
    private String password;

    @Size(min = 10, max = 10, message = "Phone number's must be 10 number")
    private String phoneNumber;

    private CreateAddressRequestDto address;
}
