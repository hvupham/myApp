package org.project.myapp.Services;

import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.dtos.CategoryDTO;
import org.project.myapp.dtos.OrderDTO;
import org.project.myapp.models.Category;
import org.project.myapp.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO) throws Exception;
    Order getOrder(Long id);
    Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException;
    void deleteOrder(Long id);
    List<Order> findByUserId(Long userId);
    Page<Order> getOrdersByKeyword(String keyword, Pageable pageable);
}
