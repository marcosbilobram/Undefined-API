package br.com.undefined.api.controllers;

import br.com.undefined.api.entities.Restaurant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RestaurantController {

    public ResponseEntity<List<Restaurant>> findAll() {
        return null;
    }

    public ResponseEntity<Restaurant> findById(Long id) {
        return null;
    }

    public ResponseEntity<Void> create(Restaurant dtoBody) {
        return null;
    }

    public ResponseEntity<Void> update(Restaurant tBody, Long id) {
        return null;
    }

    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
