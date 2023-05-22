package br.com.undefined.api.entities;

import br.com.undefined.api.controllers.RestaurantController;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(builderMethodName = "restaurantBuilder")
@Table(name = "tb_und_restaurant")
public class Restaurant extends User{

    @Column(length = 25)
    private String restaurantName;

    private String logo;

    @Embedded
    private Phone phone;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Order> orders;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Rating> ratings;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Product> products;

    public Restaurant(String restaurantName, Phone phone) {
        this.restaurantName = restaurantName;
        this.phone = phone;
    }

    public EntityModel<Restaurant> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(RestaurantController.class).findById(this.getId())).withSelfRel(),
                linkTo(methodOn(RestaurantController.class).delete(this.getId())).withRel("delete"),
                linkTo(methodOn(RestaurantController.class).findAll( Pageable.unpaged())).withRel("all")
        );
    }
}