package org.project.myapp.Services;

import lombok.RequiredArgsConstructor;
import org.project.myapp.Repositoris.OrderDetailRepository;
import org.project.myapp.Repositoris.ProductRepository;
import org.project.myapp.Repositoris.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OrderService implements IOrderService{
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

}
