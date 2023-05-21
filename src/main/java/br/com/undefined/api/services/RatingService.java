package br.com.undefined.api.services;

import br.com.undefined.api.dto.OrderDTO;
import br.com.undefined.api.dto.RatingDTO;
import br.com.undefined.api.entities.Answer;
import br.com.undefined.api.entities.Order;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.entities.Rating;
import br.com.undefined.api.repositories.OrderRepository;
import br.com.undefined.api.repositories.RatingRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    public List<Rating> findAll(){
        return repository.findAll();
    }

    public Rating findById(Long id) {
        Optional<Rating> prd = repository.findById(id);
        return prd.orElseThrow(() -> new ObjectNotFoundException(Order.class, "Objeto não encontrado"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Rating insert(Rating rating) {
        return repository.save(rating);
    }

    public void insertAnswer(Answer answer, Long id){
        Rating rt = findById(id);
        rt.getAnswers().add(answer);
        repository.save(rt);

    }

    public Rating fromDTO(RatingDTO ratingDTO){
        return new Rating(
                ratingDTO.getStars(),
                ratingDTO.getComment(),
                ratingDTO.getDate(),
                ratingDTO.getClient(),
                ratingDTO.getProduct(),
                ratingDTO.getRestaurant()
        );
    }

}
