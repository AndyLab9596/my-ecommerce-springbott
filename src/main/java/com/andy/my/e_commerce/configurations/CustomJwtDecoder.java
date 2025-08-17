package com.andy.my.e_commerce.configurations;

import com.andy.my.e_commerce.dtos.requests.IntrospectRequestDto;
import com.andy.my.e_commerce.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Value("${jwt.signer-key}")
    protected String SIGNER_KEY;

    @Override
    public Jwt decode(String token) throws JwtException {
         var response = authenticationService.introspect(
                 IntrospectRequestDto.builder().token(token).build()
         );

         if (!response.isValid()) throw new JwtException("Token invalid");

         if (Objects.isNull(nimbusJwtDecoder)) {
             SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
             nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                     .macAlgorithm(MacAlgorithm.HS512)
                     .build();
         }

        return nimbusJwtDecoder.decode(token);
    }
}
