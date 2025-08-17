package com.andy.my.e_commerce.services;

import com.andy.my.e_commerce.dtos.requests.SignUpRequestDto;
import com.andy.my.e_commerce.dtos.responses.SignUpResponseDto;

public interface AuthenticationService {
    SignUpResponseDto signUp(SignUpRequestDto dto);
}
