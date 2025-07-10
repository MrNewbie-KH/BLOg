package playground.blog.service;

import org.springframework.stereotype.Service;
import playground.blog.dto.category.CategoryRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategoryById(Long id);
    CategoryResponseDTO getCategoryByName(String name);
    CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO deleteCategory(Long id);
}
