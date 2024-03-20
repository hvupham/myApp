package org.project.myapp.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO {
    @NotEmpty(message = "Category's name can't be empty")
    private String name;
}
