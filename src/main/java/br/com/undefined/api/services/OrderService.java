package br.com.undefined.api.services;

import br.com.undefined.api.dto.OrderDTO;
import br.com.undefined.api.entities.Order;
import br.com.undefined.api.entities.Product;
import br.com.undefined.api.repositories.OrderRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> prd = repository.findById(id);
        return prd.orElseThrow(() -> new ObjectNotFoundException(Order.class, "Objeto não encontrado"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Order insert(Order order) {
        order.setTotalValue(calculateTotalValue(order.getProducts()));
        //checkIfIsPaid(order.getId())
        return repository.save(order);
    }

    /*public Order update(Order product) {
        Order ord = findById(product.getId());
        dataUpdate(ord, product);
        return repository.save(ord);
    }

    public void dataUpdate(Order orderToAtt, Order order) {
        orderToAtt.setName(order.getName());
        orderToAtt.setPricePerUnit(order.getPricePerUnit());
        orderToAtt.setDescription(order.getDescription());
    }*/

    public Double calculateTotalValue(List<Product> productList) {
        Double value = 0.0;
        for (Product pr : productList) {
            value += pr.getPricePerUnit();
        }
        return value;
    }

    public Order fromDTO(OrderDTO orderDTO){
        return new Order(
                orderDTO.getTotalValue(),
                orderDTO.getDate(),
                orderDTO.getStatus(),
                orderDTO.getClient(),
                orderDTO.getProducts(),
                orderDTO.getRestaurant()
        );
    }

}
