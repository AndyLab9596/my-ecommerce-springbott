package com.andy.my.e_commerce.servicies;

import com.andy.my.e_commerce.dtos.requests.CreateUserRequestDto;
import com.andy.my.e_commerce.dtos.responses.CreateUserResponseDto;

public interface UserService {
    CreateUserResponseDto createUser(CreateUserRequestDto request);
}
