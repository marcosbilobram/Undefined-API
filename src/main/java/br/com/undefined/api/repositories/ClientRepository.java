package br.com.undefined.api.repositories;

import br.com.undefined.api.entities.Client;
import br.com.undefined.api.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends UserRepository<Client> {

    Optional<User> findByEmail(String email);

}
