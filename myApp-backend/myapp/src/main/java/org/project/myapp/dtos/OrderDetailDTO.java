package org.project.myapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderDetailDTO {

    @JsonProperty("order_id")
    private Long orderId;
    @Min(value = 1, message = "order's Id must be >0")
    

    @JsonProperty("product_id")
    private Long productId;
    @Min(value = 1, message = "order's Id must be >0")

    @Min(value = 0, message = "Price must be >=0")
    private Float price;

    @Min(value = 1, message = "Number_of_product must be >=1")
    @JsonProperty("number_of_product")
    private int numberOfProduct;

    @Min(value = 0, message = "Total_money must be >=0")
    @JsonProperty("total_money")
    private Float totalMoney;

    private String color;
}
