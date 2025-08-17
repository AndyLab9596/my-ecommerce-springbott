package com.andy.my.e_commerce.controllers;

import com.andy.my.e_commerce.dtos.ApiResponse;
import com.andy.my.e_commerce.dtos.responses.GetAllUsersResponseDto;
import com.andy.my.e_commerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse<Page<GetAllUsersResponseDto>> getAllUsers(Pageable pageable) {
        return ApiResponse.<Page<GetAllUsersResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .result(userService.listUsers(pageable))
                .build();
    }
}
