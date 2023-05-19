package br.com.undefined.api.services;

import br.com.undefined.api.dto.CategoryDTO;
import br.com.undefined.api.entities.Category;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.repositories.CategoryRepository;
import br.com.undefined.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    @Autowired
    private CategoryRepository categRepo;

    @Autowired
    private ProductRepository prodRepo;

    public List<Category> findAll(){
        return categRepo.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> ctg = categRepo.findById(id);
        return ctg.get();
    }

    public void delete(Long id) {
        categRepo.deleteById(id);
    }

    public Category insert(Category category) {
        return categRepo.save(category);
    }

    public Category update(Category category) {
        Category ctg = findById(category.getId());
        dataUpdate(ctg, category);
        return categRepo.save(ctg);
    }

    public void dataUpdate(Category categToAtt, Category category) {
        categToAtt.setName(category.getName());
        categToAtt.setDescription(category.getDescription());
    }

    public Category fromDTO(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getName(), categoryDTO.getDescription());
    }

    public void populateRelationAttributesWithNonStoredItens(Long categoryId, List<Product> products){
        Category ctg = findById(categoryId);

        for(Product p : products){
            prodRepo.save(p);
            ctg.getProductList().add(p);
            p.getCategories().add(ctg);
            prodRepo.save(p);
            categRepo.save(ctg);
        }
    }

    public void populateRelationAttributesWithStoredItems(Long categoryId, List<Integer> productsId){
        Category ctg = findById(categoryId);

        for(Integer i : productsId){
            Product p = prodRepo.findById(Long.valueOf(i)).get();
            ctg.getProductList().add(p);
            p.getCategories().add(ctg);
            prodRepo.save(p);
            categRepo.save(ctg);
        }
    }

}
