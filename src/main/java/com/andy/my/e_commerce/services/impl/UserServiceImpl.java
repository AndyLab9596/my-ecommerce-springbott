package com.andy.my.e_commerce.services.impl;

import com.andy.my.e_commerce.dtos.responses.GetAllUsersResponseDto;
import com.andy.my.e_commerce.mappers.UserMapper;
import com.andy.my.e_commerce.repositories.UserRepository;
import com.andy.my.e_commerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<GetAllUsersResponseDto> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toGetAllUsersResponseDto);
    }
}
