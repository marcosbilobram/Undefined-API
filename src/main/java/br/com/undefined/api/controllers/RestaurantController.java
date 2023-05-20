package br.com.undefined.api.controllers;

import br.com.undefined.api.dto.RestaurantDTO;
import br.com.undefined.api.entities.Restaurant;
import br.com.undefined.api.services.RestaurantService;
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

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    PagedResourcesAssembler<Restaurant> assembler;

    @GetMapping(value = "/all")
    public PagedModel<EntityModel<Restaurant>> findAll(@ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        Page<Restaurant> rests = restaurantService.findAll(pageable);
        return assembler.toModel(rests);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.ok().body(restaurant);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> create(RestaurantDTO dtoBody) {
        Restaurant restaurant = restaurantService.fromDTO(dtoBody);
        restaurant = restaurantService.insert(restaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody RestaurantDTO restaurantDTO, @PathVariable Long id){
        Restaurant restaurant = restaurantService.fromDTO(restaurantDTO);
        restaurant.setId(id);
        restaurantService.update(restaurant);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<RestaurantDTO>> findByName(@RequestParam("name") String name){
        List<Restaurant> list = restaurantService.findByName(name);
        List<RestaurantDTO> listDTO = list.stream().map(RestaurantDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}
