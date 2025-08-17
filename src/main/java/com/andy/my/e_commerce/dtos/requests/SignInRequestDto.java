package com.andy.my.e_commerce.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequestDto {
    @Email
    private String email;

    @Size(min = 5, max = 10, message = "USER_PASSWORD_IS_NOT_VALID")
    private String password;
}
