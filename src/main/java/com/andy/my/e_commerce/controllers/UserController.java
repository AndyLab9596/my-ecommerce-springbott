package com.andy.my.e_commerce.controllers;

import com.andy.my.e_commerce.dtos.ApiResponse;
import com.andy.my.e_commerce.dtos.requests.CreateUserRequestDto;
import com.andy.my.e_commerce.dtos.responses.CreateUserResponseDto;
import com.andy.my.e_commerce.servicies.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    ApiResponse<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto request) {
        var result = userService.createUser(request);
        return ApiResponse.<CreateUserResponseDto>builder().result(result).build();
    }

}
