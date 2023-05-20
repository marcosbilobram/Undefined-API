package br.com.undefined.api.services;

import br.com.undefined.api.dto.OrderDTO;
import br.com.undefined.api.dto.RestaurantDTO;
import br.com.undefined.api.entities.Order;
import br.com.undefined.api.entities.Restaurant;
import br.com.undefined.api.repositories.RestaurantRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RestaurantService {

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

    public Restaurant insert(Restaurant order) {
        return repository.save(order);
    }

    public Restaurant update(Restaurant product) {
        Restaurant ord = findById(product.getId());
        dataUpdate(ord, product);
        return repository.save(ord);
    }

    public List<Restaurant> findByName(String name) {
        return repository.findByNameContaining(name);
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

}
