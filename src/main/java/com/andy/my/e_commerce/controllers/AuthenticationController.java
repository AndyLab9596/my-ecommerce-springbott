package com.andy.my.e_commerce.controllers;

import com.andy.my.e_commerce.dtos.ApiResponse;
import com.andy.my.e_commerce.dtos.requests.SignInRequestDto;
import com.andy.my.e_commerce.dtos.requests.SignUpRequestDto;
import com.andy.my.e_commerce.dtos.responses.SignInResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignUpResponseDto;
import com.andy.my.e_commerce.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/sign-up")
    public ApiResponse<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
        var result = authenticationService.signUp(requestDto);
        return ApiResponse.<SignUpResponseDto>builder()
                .message("sign up successfully")
                .code(HttpStatus.CREATED.value())
                .result(result)
                .build();
    }

    @PostMapping(path = "/sign-in")
    public ApiResponse<SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestDto) {
        var result = authenticationService.signIn(requestDto);
        return ApiResponse.<SignInResponseDto>builder()
                .message("sign in successfully")
                .code(HttpStatus.OK.value())
                .result(result)
                .build();
    }
}
