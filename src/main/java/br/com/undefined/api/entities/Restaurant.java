package br.com.undefined.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_und_restaurant")
public class Restaurant extends User{

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
}