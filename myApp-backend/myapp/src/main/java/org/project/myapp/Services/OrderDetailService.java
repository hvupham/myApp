package org.project.myapp.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.Repositoris.OrderDetailRepository;
import org.project.myapp.Repositoris.OrderRepository;
import org.project.myapp.Repositoris.ProductRepository;
import org.project.myapp.dtos.OrderDetailDTO;
import org.project.myapp.models.Order;
import org.project.myapp.models.OrderDetail;
import org.project.myapp.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class OrderDetailService implements IOrderDetailService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    @Transactional
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        // tim xem orderId co ton tai khong
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("can not find order with id"+orderDetailDTO.getOrderId()));

        //tim product theo id
        Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find product with id: " + orderDetailDTO.getProductId()));


        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .numberOfProducts(orderDetailDTO.getNumberOfProduct())
                .price(orderDetailDTO.getPrice())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor())
                .build();
        //lưu vào db
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id).orElseThrow(
                ()-> new DataNotFoundException("can not find orderDetail with id "+ id)
        );
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        // tim xem orderDetail co ton tai khong
        OrderDetail existingOrderDeatil = orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("can not find orderDetail with id "+ id)
        );

        //
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("can not find order with id "+ id)
        );

        Product existingProduct = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find product with id: " + orderDetailDTO.getProductId()));

        existingOrderDeatil.setPrice(orderDetailDTO.getPrice());
        existingOrderDeatil.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDeatil.setColor(orderDetailDTO.getColor());
        existingOrderDeatil.setNumberOfProducts(orderDetailDTO.getNumberOfProduct());
        existingOrderDeatil.setOrder(existingOrder);
        existingOrderDeatil.setProduct(existingProduct);

        return orderDetailRepository.save(existingOrderDeatil);

    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
