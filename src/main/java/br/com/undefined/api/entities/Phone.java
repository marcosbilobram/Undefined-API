package br.com.undefined.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {

    @Column(length = 3)
    private Integer DDI;

    @Column(length = 2)
    private Integer DDD;

    @Column(length = 12)
    private String phoneNumber;
}
