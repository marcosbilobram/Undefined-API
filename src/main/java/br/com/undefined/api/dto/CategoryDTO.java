package br.com.undefined.api.dto;

import br.com.undefined.api.entities.Category;
import br.com.undefined.api.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDTO {

    private String name;
    private String description;
    private List<Product> productList;

    public CategoryDTO(Category category){
        this.name = category.getName();
        this.description = category.getDescription();
        this.productList = category.getProductList();
    }

}
