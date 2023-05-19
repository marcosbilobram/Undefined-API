package br.com.undefined.api.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import br.com.undefined.api.entities.Credential;
import br.com.undefined.api.entities.Token;
import br.com.undefined.api.entities.User;
import br.com.undefined.api.repositories.ClientRepository;
import br.com.undefined.api.repositories.RestaurantRepository;
import br.com.undefined.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.validation.Valid;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(@Valid Credential credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withSubject(credencial.email())
                .withIssuer("Undefined")
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign(alg)
                ;
        return new Token(token, "JWT", "Bearer");
    }

    public User getValidateUser(String token) throws Throwable {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                .withIssuer("Undefined")
                .build()
                .verify(token)
                .getSubject()
                ;

        return (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }



}
