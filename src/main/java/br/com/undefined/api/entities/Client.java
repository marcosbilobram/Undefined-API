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
@Table(name = "tb_und_client")
public class Client extends User {

    @Column(length = 25, nullable = false)
    private String clientName;

    @Embedded
    private Phone phone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToMany(mappedBy = "client", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<Rating> ratings;
}