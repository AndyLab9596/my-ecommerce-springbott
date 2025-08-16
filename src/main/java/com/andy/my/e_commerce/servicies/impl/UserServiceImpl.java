package com.andy.my.e_commerce.servicies.impl;

import com.andy.my.e_commerce.dtos.requests.CreateUserRequestDto;
import com.andy.my.e_commerce.dtos.responses.CreateUserResponseDto;
import com.andy.my.e_commerce.entities.User;
import com.andy.my.e_commerce.enums.UserRole;
import com.andy.my.e_commerce.exception.AppException;
import com.andy.my.e_commerce.exception.ErrorCode;
import com.andy.my.e_commerce.mappers.UserMapper;
import com.andy.my.e_commerce.repositories.AddressRepository;
import com.andy.my.e_commerce.repositories.UserRepository;
import com.andy.my.e_commerce.servicies.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto request) {
        boolean isUserWithEmailExisted = userRepository.existsByEmail(request.getEmail());
        if (isUserWithEmailExisted) {
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);
        }
        log.info(request.toString());
        User user = userMapper.fromCreateUserRequestDto(request);
        user.setUserRole(UserRole.USER);
        user = userRepository.save(user);

        // TODO: If address is already in DB, there no need to create a new one.

        addressRepository.save(user.getAddress());

        return userMapper.toCreateUserResponseDto(user);
    }
}
