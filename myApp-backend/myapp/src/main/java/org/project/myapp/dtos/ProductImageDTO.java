package org.project.myapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {
    @JsonProperty("product_id")
    @Min(value = 1, message = "user's Id must be >0")
    private Long productId;

    @Size(min = 5, max=200, message = "Image's name")
    @JsonProperty("image_url")
    private String imageUrl;
}
