package br.com.undefined.api.dto;

import br.com.undefined.api.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDTO {

    private Double totalValue;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar date;

    private Enum<OrderStatus> status;

    private Client client;

    private List<Product> products;

    private Restaurant restaurant;

    public OrderDTO(Order order) {
        this.totalValue = order.getTotalValue();
        this.date = order.getDate();
        this.status = order.getStatus();
        this.client = order.getClient();
        this.products = order.getProducts();
        this.restaurant = order.getRestaurant();
    }

}
