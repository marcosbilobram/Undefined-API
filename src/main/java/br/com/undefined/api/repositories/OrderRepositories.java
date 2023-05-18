package br.com.undefined.api.repositories;

import br.com.undefined.api.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositories extends JpaRepository<Order, Long> {
}
