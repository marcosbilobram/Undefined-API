package br.com.undefined.api.services;

import br.com.undefined.api.dto.ProductDTO;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.repositories.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> findAll(){
        return productRepo.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> prd = productRepo.findById(id);
        return prd.orElseThrow(() -> new ObjectNotFoundException(Product.class, "produto não encontrado"));
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public Product insert(Product product) {
        return productRepo.save(product);
    }

    public Product update(Product product) {
        Product prod = findById(product.getId());
        dataUpdate(prod, product);
        return productRepo.save(prod);
    }

    public void dataUpdate(Product prodToAtt, Product product) {
        prodToAtt.setName(product.getName());
        prodToAtt.setPricePerUnit(product.getPricePerUnit());
        prodToAtt.setDescription(product.getDescription());
    }

    public Product fromDTO(ProductDTO productDTO){
        return new Product(
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getImage(),
                productDTO.getPricePerUnit(),
                productDTO.getQuantity(),
                productDTO.getCategories(),
                productDTO.getRestaurant(),
                productDTO.getOrders()
        );
    }

    public List<Product> findByName(String name){
        String parseNM = name.toLowerCase();
        List<Product> productIM = productRepo.findAll();
        List<Product> productResp = new ArrayList<>();

        try {
            for (Product prd: productIM) {
                if ((prd.getName().toLowerCase()).contains(parseNM)){
                    productResp.add(prd);
                }
            }
        }
        catch (ObjectNotFoundException e){
            e.getMessage();
        }
        if (productResp.isEmpty()){
            throw new ObjectNotFoundException(Product.class, "Produto não encontrado");
        }
        return productResp;
    }

    public List<Product> findProductsByCategoryName(String categoryName){
        return productRepo.findByCategoriesName(categoryName);
    }
}
