package com.andy.my.e_commerce.mappers;

import com.andy.my.e_commerce.dtos.requests.CreateAddressRequestDto;
import com.andy.my.e_commerce.dtos.requests.CreateUserRequestDto;
import com.andy.my.e_commerce.dtos.responses.CreateAddressResponseDto;
import com.andy.my.e_commerce.dtos.responses.CreateUserResponseDto;
import com.andy.my.e_commerce.entities.Address;
import com.andy.my.e_commerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User fromCreateUserRequestDto(CreateUserRequestDto dto);
    Address fromCreateAddressRequestDto(CreateAddressRequestDto dto);

    CreateUserResponseDto toCreateUserResponseDto(User user);
    CreateAddressResponseDto toCreateAddressResponseDto(Address address);
}
