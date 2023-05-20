package br.com.undefined.api.controllers;

import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import br.com.undefined.api.dto.ProductDTO;
import br.com.undefined.api.dto.RatingDTO;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.entities.Rating;
import br.com.undefined.api.repositories.RatingRepository;
import br.com.undefined.api.services.ProductService;
import br.com.undefined.api.services.RatingService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public  ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> prod = productService.findAll();
        List<ProductDTO> listDTO = prod.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        Product prod = productService.findById(id);
        return ResponseEntity.ok().body(new ProductDTO(prod));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid ProductDTO productDTO) {
        Product prd = productService.fromDTO(productDTO);
        prd = productService.insert(prd);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prd.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid ProductDTO productDTO, @PathVariable Long id){
        Product prd = productService.fromDTO(productDTO);
        prd.setId(id);
        prd = productService.update(prd);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<ProductDTO>> findByName(@RequestParam("name") String name){
        List<Product> list = productService.findByName(name);
        List<ProductDTO> listDTO = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/ctgs")
    public ResponseEntity<List<ProductDTO>> findProductsByCategoryName(@RequestParam("name") String categoryName){
        List<Product> list = productService.findProductsByCategoryName(categoryName);
        List<ProductDTO> listDTO = list.stream().map(x -> new ProductDTO(x.getName(), x.getDescription(), x.getImage(), x.getPricePerUnit(), x.getQuantity(), x.getRestaurant())).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/ratings/{id}")
    public PagedModel<EntityModel<Object>> findAllProductRatings(@ParameterObject @PageableDefault(size = 5) Pageable pageable, @PathVariable Long productId) {
        Page<Rating> ratings = repository.findAll(pageable);
        //Page<RatingDTO> ratingsDTO = (Page<RatingDTO>) ratings.stream().map(rt -> new RatingDTO(rt)).collect(Collectors.toList());

        return assembler.toModel(ratings.map(Rating::toEntityModel));
    }

    @PostMapping(value = "/{id}/ratings/add")
    public ResponseEntity<Void> addCommentToProduct(@RequestParam @Valid RatingDTO ratingDTO, @PathVariable Long id) {
        ratingDTO.setDate(Calendar.getInstance());
        Rating rt = ratingService.fromDTO(ratingDTO);
        productService.AddNewRatingToProduct(rt, id);
        return null;
    }
}
