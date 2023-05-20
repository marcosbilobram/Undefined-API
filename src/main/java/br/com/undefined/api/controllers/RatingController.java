package br.com.undefined.api.controllers;

import br.com.undefined.api.entities.Rating;
import br.com.undefined.api.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class RatingController {

    @Autowired
    private RatingRepository repository;

    public ResponseEntity<Rating> findById(Long id) {
        return null;
    }

    public ResponseEntity<Void> update(Rating tBody, Long id) {
        return null;
    }

    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
