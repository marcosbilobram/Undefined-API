package br.com.undefined.api.services;

import br.com.undefined.api.dto.RestaurantDTO;
import br.com.undefined.api.entities.Restaurant;
import br.com.undefined.api.repositories.RestaurantRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RestaurantService implements UserDetailsService {

    @Autowired
    private RestaurantRepository repository;

    public Page<Restaurant> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Restaurant findById(Long id) {
        Optional<Restaurant> prd = repository.findById(id);
        return prd.orElseThrow(() -> new ObjectNotFoundException(Restaurant.class, "Objeto não encontrado"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Restaurant insert(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant ord = findById(restaurant.getId());
        dataUpdate(ord, restaurant);
        return repository.save(ord);
    }

    public List<Restaurant> findByName(String name) {
        return repository.findByRestaurantNameContaining(name);
    }

    public void dataUpdate(Restaurant restToAtt, Restaurant restaurant) {
        restToAtt.setRestaurantName(restaurant.getRestaurantName());
        restToAtt.setLogo(restaurant.getLogo());
        restToAtt.setPhone(restaurant.getPhone());
    }

    public Restaurant fromDTO(RestaurantDTO restaurantDTO){
        return new Restaurant(
                restaurantDTO.getRestaurantName(),
                restaurantDTO.getPhone()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));
    }
}
