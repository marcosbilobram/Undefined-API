package br.com.undefined.api.controllers;

import br.com.undefined.api.dto.AnswerDTO;
import br.com.undefined.api.dto.ProductDTO;
import br.com.undefined.api.dto.RatingDTO;
import br.com.undefined.api.entities.Answer;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.entities.Rating;
import br.com.undefined.api.services.ProductService;
import br.com.undefined.api.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private PagedResourcesAssembler<Object> assembler;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        Product prod = productService.findById(id);
        return ResponseEntity.ok().body(new ProductDTO(prod));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> insert(@RequestBody ProductDTO productDTO) {
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

    @DeleteMapping(value = "/{id}/del")
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

    /*@GetMapping(value = "/ratings/{id}")
    public PagedModel<EntityModel<Object>> findAllProductRatings(@ParameterObject @PageableDefault(size = 5) Pageable pageable, @PathVariable Long productId) {
        //Page<Rating> ratings = ratingService.findAll(pageable);
        //Page<RatingDTO> ratingsDTO = (Page<RatingDTO>) ratings.stream().map(rt -> new RatingDTO(rt)).collect(Collectors.toList());

        return assembler.toModel(ratings.map(Rating::toEntityModel));
    }*/

    @PostMapping(value = "/{id}/ratings/add")
    public ResponseEntity<Void> addCommentToProduct(@RequestParam @Valid RatingDTO ratingDTO, @PathVariable Long id) {
        ratingDTO.setDate(Calendar.getInstance());
        Rating rt = ratingService.fromDTO(ratingDTO);
        productService.AddNewRatingToProduct(rt, id);
        return null;
    }

    //addAnswerToComment
    @PostMapping(value = "/{product_id}/ratings/{id}")
    public ResponseEntity<Void> addAnswerToComment(@PathVariable("product_id") Long productId, @ RequestBody Answer answer, @PathVariable("id") Long id){
        ratingService.insertAnswer(answer, id);
        return ResponseEntity.ok().build();
    }
}
