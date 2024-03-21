package org.project.myapp.Controller;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.Services.OrderDetailService;
import org.project.myapp.dtos.OrderDetailDTO;
import org.project.myapp.models.OrderDetail;
import org.project.myapp.responses.OrderDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    //them mot order detail
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO
            ){
        try {
            OrderDetail newOrderDetail =orderDetailService.createOrderDetail(orderDetailDTO);

            return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(newOrderDetail));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOderDitail(
            @Valid @PathVariable("id") Long id
    ) throws DataNotFoundException {
        OrderDetail orderDetail= orderDetailService.getOrderDetail(id);
        return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(orderDetail));

//        return ResponseEntity.ok(orderDetail);
    }
    //lay ra order detail tu mot order nao do
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(
            @Valid @PathVariable("orderId")
            Long orderId){
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponses = orderDetails
                .stream()
                .map(OrderDetailResponse::fromOrderDetail)
                .toList();
        return ResponseEntity.ok(orderDetailResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable ("id") Long id,
            @RequestBody OrderDetailDTO orderDetailDTO
    ){
        try {
            OrderDetail orderDetail= orderDetailService.updateOrderDetail(id, orderDetailDTO );
            return ResponseEntity.ok().body(orderDetail);

        } catch (DataNotFoundException e) {

            return ResponseEntity.badRequest().body(e.getMessage());


        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(
            @Valid @PathVariable("id") Long id
    ){
        orderDetailService.deleteById(id);
        return ResponseEntity.ok().body("delete orderDetail with id "+id+" successfully");
    }
}
