package br.com.undefined.api.dto;

import br.com.undefined.api.entities.Order;
import br.com.undefined.api.entities.Phone;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.entities.Rating;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantDTO {

    private String restaurantName;

    private String logo;

    @Embedded
    private Phone phone;

    private List<Order> orders;

    private List<Rating> ratings;

    private List<Product> products;

}
