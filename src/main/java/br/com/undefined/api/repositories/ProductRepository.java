package br.com.undefined.api.repositories;

import br.com.undefined.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContaining(String name);

    List<Product> findByCategoriesName(String name);

}
