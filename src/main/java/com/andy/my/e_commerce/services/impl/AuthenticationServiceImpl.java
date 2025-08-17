package com.andy.my.e_commerce.services.impl;

import com.andy.my.e_commerce.dtos.requests.IntrospectRequestDto;
import com.andy.my.e_commerce.dtos.requests.SignInRequestDto;
import com.andy.my.e_commerce.dtos.requests.SignUpRequestDto;
import com.andy.my.e_commerce.dtos.responses.IntrospectResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignInResponseDto;
import com.andy.my.e_commerce.dtos.responses.SignUpResponseDto;
import com.andy.my.e_commerce.entities.User;
import com.andy.my.e_commerce.enums.UserRole;
import com.andy.my.e_commerce.exception.AppException;
import com.andy.my.e_commerce.exception.ErrorCode;
import com.andy.my.e_commerce.mappers.UserMapper;
import com.andy.my.e_commerce.repositories.AddressRepository;
import com.andy.my.e_commerce.repositories.UserRepository;
import com.andy.my.e_commerce.services.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;

    @NonFinal
    @Value("${jwt.signer-key}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.access-token-duration}")
    protected String ACCESS_TOKEN_DURATION;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        boolean isEmailRegisteredExisted = userRepository.existsByEmail(requestDto.getEmail());
        if (isEmailRegisteredExisted) {
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);
        }
        var user = userMapper.fromSignUpRequestDto(requestDto);
        user.setUserRole(UserRole.USER);
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        var address = user.getAddress();

        if (address != null) {
            address = addressRepository.save(address);
            user.setAddress(address);
        }

        user = userRepository.save(user);

        return userMapper.toSignUpResponseDto(user);
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto requestDto) {
        var user = userRepository
                .findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_CREDENTIAL));
        boolean isPasswordMatched = passwordEncoder().matches(requestDto.getPassword(),user.getPassword());

        var accessToken = generateToken(user);

        if (!isPasswordMatched) {
            throw new AppException(ErrorCode.INVALID_CREDENTIAL);
        }

        return SignInResponseDto.builder()
                .accessToken(accessToken)
                .user(userMapper.toSignInUserResponseDto(user))
                .build();
    }

    @Override
    public IntrospectResponseDto introspect(IntrospectRequestDto requestDto) {
        var token = requestDto.getToken();
        boolean isValid = true;
        try {
            verifyToken(token);
        } catch (AppException | JOSEException | ParseException e) {
            isValid = false;
        }

        return IntrospectResponseDto.builder().isValid(isValid).build();
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expirationTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getName())
                .issuer("andy.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(Integer.parseInt(ACCESS_TOKEN_DURATION), ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (user.getUserRole() != null) {
            stringJoiner.add("ROLE_" + user.getUserRole());
        }
        return stringJoiner.toString();
    }

    private PasswordEncoder passwordEncoder() {
        int SALT = 10;
        return new BCryptPasswordEncoder(SALT);
    }
}
