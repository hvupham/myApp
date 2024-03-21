package org.project.myapp.Services;

import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.dtos.OrderDTO;
import org.project.myapp.models.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO) throws Exception;
    Order getOrder(Long id);
    Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException;
    void deleteOrder(Long id);
    List<Order> findByUserId(Long userId);
}
