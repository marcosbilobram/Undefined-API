package br.com.undefined.api.dto;

import br.com.undefined.api.entities.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
public class OrderCreationDTO {

    private Double totalValue;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar date;

    private Enum<OrderStatus> status;

    private Long clientId;

    private List<Long> productsId;

    private Long restaurantId;

    public OrderCreationDTO(Double totalValue, Enum<OrderStatus> status, Long clientId, List<Long> productsId, Long restaurantId) {
        this.totalValue = totalValue;
        this.date = Calendar.getInstance();
        this.status = status;
        this.clientId = clientId;
        this.productsId = productsId;
        this.restaurantId = restaurantId;
    }
}
