package br.com.undefined.api.repositories;

import br.com.undefined.api.entities.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends UserRepository<Restaurant> {
    Optional<Restaurant> findByEmail(String email);
}
