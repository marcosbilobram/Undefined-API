package br.com.undefined.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_und_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_prod_categ",
            joinColumns =
            @JoinColumn(
                    name = "prod_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "categ_id",
                    referencedColumnName = "id"
            )
    )
    private List<Product> productList;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description, List<Product> productList) {
        this.name = name;
        this.description = description;
        this.productList = productList;
    }
}
