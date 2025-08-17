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
public class SignUpRequestDto {
    @NotBlank(message = "USER_NAME_IS_REQUIRED")
    private String name;

    @Email
    private String email;

    @Size(min = 5, max = 10, message = "USER_PASSWORD_IS_NOT_VALID")
    private String password;

    private String phoneNumber;

    private SignUpAddressRequestDto address;
}
