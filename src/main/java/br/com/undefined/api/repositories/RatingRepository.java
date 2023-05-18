package br.com.undefined.api.repositories;

import br.com.undefined.api.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
