package com.andy.my.e_commerce.services;

import com.andy.my.e_commerce.dtos.responses.GetAllUsersResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<GetAllUsersResponseDto> listUsers(Pageable pageable);
}
