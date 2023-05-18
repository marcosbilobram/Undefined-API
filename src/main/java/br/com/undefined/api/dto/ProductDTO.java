package br.com.undefined.api.dto;

import br.com.undefined.api.entities.Category;
import br.com.undefined.api.entities.Order;
import br.com.undefined.api.entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {

    private String name;

    private String description;

    private String image;

    private Double pricePerUnit;

    private Integer quantity = 0;

    private List<Category> categories;

    private Restaurant restaurant;

    private List<Order> orders;

}
