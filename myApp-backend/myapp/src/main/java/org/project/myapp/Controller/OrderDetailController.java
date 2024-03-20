package org.project.myapp.Controller;
import org.project.myapp.dtos.OrderDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {

    //them mot order detail
    @PostMapping
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO newOrderDetail
            ){
        return ResponseEntity.ok("create orderDetail here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOderDitail(
            @Valid @PathVariable("id") Long id
    ){
        return ResponseEntity.ok("getOrderDetail with id "+id);
    }
    //lay ra order detail tu mot order nao do
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId){
        return ResponseEntity.ok(Collections.singletonList("getOrderDetail with id = " + orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable ("id") Long id,
            @RequestBody OrderDetailDTO newOrderDetailData
    ){
        return ResponseEntity.ok("UpdateOrderDetail with id ="+id +
                "new orderDetailData: "+newOrderDetailData);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(
            @Valid @PathVariable("id") Long id
    ){
        return ResponseEntity.noContent().build();
    }
}
