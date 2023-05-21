package br.com.undefined.api.services;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
/*
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
    }*/



}
