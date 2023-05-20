package br.com.undefined.api.entities;

import br.com.undefined.api.controllers.ProductController;
import br.com.undefined.api.controllers.RatingController;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.Calendar;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_und_rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 5)
    private Integer stars;
    private String comment;

    //@ElementCollection(targetClass = Answer.class)
    //@CollectionTable(name = "tb_und_rating_answers", joinColumns = @JoinColumn(name = "tb_booking_room_id"), foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    @OneToMany(targetEntity = Answer.class)
    private List<Answer> answers;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar date;

    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.DETACH,  fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Rating(Integer stars, String comment, Calendar date, Client client, Product product, Restaurant restaurant) {
        this.stars = stars;
        this.comment = comment;
        this.date = date;
        this.client = client;
        this.product = product;
        this.restaurant = restaurant;
    }

    public EntityModel<Rating> toEntityModel(){
        return EntityModel.of(
                null
                //linkTo(methodOn(RatingController.class).findById(id)).withSelfRel(),
                //linkTo(methodOn(RatingController.class).delete(id)).withRel("delete"),
                //linkTo(methodOn(ProductController.class).findAllProductRatings(Pageable.unpaged(), product.getId())).withRel("all")
        );
    }

}

