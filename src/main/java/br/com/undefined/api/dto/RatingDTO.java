package br.com.undefined.api.dto;

import br.com.undefined.api.entities.Client;
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
public class RatingDTO {

    private Integer stars;
    private String comment;
    private List<String> answers;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar date;

    private Client client;

    private Restaurant restaurant;

}
