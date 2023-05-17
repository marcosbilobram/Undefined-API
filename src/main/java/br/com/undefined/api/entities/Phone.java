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

    @Column(nullable = false, length = 3)
    private Integer DDI;

    @Column(nullable = false, length = 2)
    private Integer DDD;

    @Column(nullable = false, length = 12)
    private String phoneNumber;
}
