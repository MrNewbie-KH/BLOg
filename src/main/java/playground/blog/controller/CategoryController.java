package playground.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import playground.blog.dto.category.CategoryRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
private final CategoryService categoryService;
@PostMapping
public CategoryResponseDTO createCategory( @RequestBody CategoryRequestDTO categoryRequestDTO){
    return categoryService.createCategory(categoryRequestDTO);
}
@GetMapping
public List<CategoryResponseDTO> getAllCategories(){
    return categoryService.getAllCategories();
}
@GetMapping("/{id}")
public CategoryResponseDTO getCategoryById(@PathVariable Long id){
    return categoryService.getCategoryById(id);
}
@GetMapping("/search")
public CategoryResponseDTO getCategoryByName(@RequestParam("name") String name){
    return categoryService.getCategoryByName(name);
}
@PutMapping("/{id}")
public CategoryResponseDTO updateCategory(@PathVariable Long id,@RequestBody CategoryRequestDTO categoryRequestDTO){
    return categoryService.updateCategory(id,categoryRequestDTO);
}
@DeleteMapping("/{id}")
public CategoryResponseDTO deleteCategory(@PathVariable Long id){
    return categoryService.deleteCategory(id);
}

}
