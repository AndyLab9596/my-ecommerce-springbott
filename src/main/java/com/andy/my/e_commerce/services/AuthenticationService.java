package com.andy.my.e_commerce.services;

import com.andy.my.e_commerce.dtos.requests.IntrospectRequestDto;
import com.andy.my.e_commerce.dtos.requests.SignInRequestDto;
import com.andy.my.e_commerce.dtos.requests.SignUpRequestDto;
import com.andy.my.e_commerce.dtos.responses.IntrospectResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignInResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignUpResponseDto;

public interface AuthenticationService {
    SignUpResponseDto signUp(SignUpRequestDto dto);
    SignInResponseDto signIn(SignInRequestDto dto);
    IntrospectResponseDto introspect(IntrospectRequestDto dto);
}
