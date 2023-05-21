package br.com.undefined.api.repositories;

import br.com.undefined.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    Optional<User> findByEmail(String email);

}
