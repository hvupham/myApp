package org.project.myapp.Controller;

import lombok.RequiredArgsConstructor;
import org.project.myapp.Services.CategoryService;
import org.project.myapp.dtos.CategoryDTO;
import org.project.myapp.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@Validated
//dependency injection
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/category") //
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategories(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ){
        if (result.hasErrors()){
            List<String> errorMessages  = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);

        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Insert category successfully");

    }
    // hien tat ca category
    @GetMapping("") // http://localhost:8088/api/v1/category?page=1&limit=10
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit

    ){
        List<Category> categories= categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }





    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategories(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO
            ){
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("Update category successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCategories(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("DeleteCategory with id = " +id+"  successfully");
    }
}
