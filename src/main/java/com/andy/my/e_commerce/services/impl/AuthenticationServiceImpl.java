package com.andy.my.e_commerce.services.impl;

import com.andy.my.e_commerce.dtos.requests.SignUpRequestDto;
import com.andy.my.e_commerce.dtos.responses.SignUpResponseDto;
import com.andy.my.e_commerce.enums.UserRole;
import com.andy.my.e_commerce.exception.AppException;
import com.andy.my.e_commerce.exception.ErrorCode;
import com.andy.my.e_commerce.mappers.UserMapper;
import com.andy.my.e_commerce.repositories.AddressRepository;
import com.andy.my.e_commerce.repositories.UserRepository;
import com.andy.my.e_commerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        boolean isEmailRegisteredExisted = userRepository.existsByEmail(requestDto.getEmail());
        if (isEmailRegisteredExisted) {
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);
        }
        var user = userMapper.fromSignUpRequestDto(requestDto);
        user.setUserRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var address = user.getAddress();

        if (address != null) {
            address = addressRepository.save(address);
            user.setAddress(address);
        }

        user = userRepository.save(user);

        return userMapper.toSignUpResponseDto(user);
    }
}
