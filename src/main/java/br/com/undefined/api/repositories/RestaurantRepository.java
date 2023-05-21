package br.com.undefined.api.repositories;

import br.com.undefined.api.entities.Product;
import br.com.undefined.api.entities.Restaurant;
import br.com.undefined.api.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends UserRepository<Restaurant> {
    Optional<User> findByEmail(String email);

    Page<Restaurant> findAll(Pageable pageable);

    List<Restaurant> findByRestaurantNameContaining(String name);

    //List<Product> findAllProductsByRestaurantId(Long id);

}
