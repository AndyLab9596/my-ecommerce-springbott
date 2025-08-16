package com.andy.my.e_commerce.dtos.responses;

import com.andy.my.e_commerce.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private UserRole userRole;
    private CreateAddressResponseDto address;
}
