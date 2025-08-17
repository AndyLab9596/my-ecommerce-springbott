package com.andy.my.e_commerce.mappers;

import com.andy.my.e_commerce.dtos.requests.SignUpAddressRequestDto;
import com.andy.my.e_commerce.dtos.requests.SignUpRequestDto;
import com.andy.my.e_commerce.dtos.responses.GetAllUsersResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignInUserResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignUpAddressResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignUpResponseDto;
import com.andy.my.e_commerce.entities.Address;
import com.andy.my.e_commerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User fromSignUpRequestDto(SignUpRequestDto dto);
    Address fromSignUpAddressRequestDto(SignUpAddressRequestDto dto);

    SignUpResponseDto toSignUpResponseDto(User user);
    SignUpAddressResponseDto toSignUpAddressResponseDto(Address address);

    GetAllUsersResponseDto toGetAllUsersResponseDto(User user);

    SignInUserResponseDto toSignInUserResponseDto(User user);
}
