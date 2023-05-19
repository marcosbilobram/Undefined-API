package br.com.undefined.api.dto;

import br.com.undefined.api.entities.Category;
import br.com.undefined.api.entities.Order;
import br.com.undefined.api.entities.Product;
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

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.pricePerUnit = product.getPricePerUnit();
        this.quantity = product.getQuantity();
        this.categories = product.getCategories();
        this.restaurant = product.getRestaurant();
        this.orders = product.getOrders();
    }

    public ProductDTO(String name, String description, String image, Double pricePerUnit, Integer quantity, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.restaurant = restaurant;
    }
}
