package br.com.undefined.api.controllers;

import br.com.undefined.api.dto.OrderCreationDTO;
import br.com.undefined.api.dto.OrderDTO;
import br.com.undefined.api.entities.Order;
import br.com.undefined.api.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<Order> orders = service.findAll();
        List<OrderDTO> orderDTOs = orders.stream().map(x -> new OrderDTO(x)).toList();
        return ResponseEntity.ok().body(orderDTOs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new OrderDTO(service.findById(id)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> create(@RequestBody @Valid OrderDTO orderDTO) {
        orderDTO.setDate(Calendar.getInstance());
        Order order = service.fromDTO(orderDTO);
        service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.findAll().size()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/add/ids")
    public ResponseEntity<Void> createWithIds(OrderCreationDTO dto) {
        service.insertWithIDs(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.findAll().size()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
