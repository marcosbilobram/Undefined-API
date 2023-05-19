package br.com.undefined.api.controllers;

import br.com.undefined.api.dto.CategoryDTO;
import br.com.undefined.api.entities.Category;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public  ResponseEntity<List<CategoryDTO>> findAll(){
        List<Category> ctg = categoryService.findAll();
        List<CategoryDTO> listDTO = ctg.stream().map( x -> new CategoryDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        Category ctg = categoryService.findById(id);
        return ResponseEntity.ok().body(new CategoryDTO(ctg));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid CategoryDTO categoryDTO){
        Category ctg = categoryService.fromDTO(categoryDTO);
        ctg = categoryService.insert(ctg);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ctg.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}/updt")
    public ResponseEntity<Void> update(@RequestBody @Valid CategoryDTO categoryDTO, @PathVariable Long id){
        Category ctgAtt = categoryService.fromDTO(categoryDTO);
        ctgAtt.setId(id);
        ctgAtt = categoryService.update(ctgAtt);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}/del")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{categoryId}/products/new")
    public ResponseEntity<Void> addNewProducts(@PathVariable Long categoryId, @RequestBody List<Product> products){
        categoryService.populateRelationAttributesWithNonStoredItens(categoryId, products);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{categoryId}/products/db")
    public ResponseEntity<Void> addProductsById(@PathVariable Long categoryId, @RequestBody List<Integer> productsId){
        categoryService.populateRelationAttributesWithStoredItems(categoryId, productsId);
        return ResponseEntity.noContent().build();
    }

}
