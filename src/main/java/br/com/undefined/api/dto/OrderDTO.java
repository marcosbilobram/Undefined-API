package br.com.undefined.api.dto;

import br.com.undefined.api.entities.Client;
import br.com.undefined.api.entities.OrderStatus;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.entities.Restaurant;
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

}
