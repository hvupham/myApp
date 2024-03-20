package org.project.myapp.Repositoris;

import org.project.myapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //tim don hang cua user nao do
    List<Order> findByUserId   (Long userId);
}
