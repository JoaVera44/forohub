package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    //Implementacion del token de auth0

public String generarToken(Usuario usuario) {

    try {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        String token = JWT.create()
                .withIssuer("forohub")
                .withSubject(usuario.getNombre())
                .withClaim("id", usuario.getId())
                .withExpiresAt(generarFechaExpiracion())
                .sign(algorithm);
        return token;
    } catch (JWTCreationException exception) {
        throw new RuntimeException();
    }

}

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        DecodedJWT verifier = null;
        if (token == null){
            throw new RuntimeException("token nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("forohub")
                    // reusable verifier instance
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("verifier invalido");
        }
        return verifier.getSubject();
    }


}
