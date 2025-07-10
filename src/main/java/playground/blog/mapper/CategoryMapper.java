package playground.blog.mapper;

import playground.blog.dto.category.CategoryRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.entity.Category;

public interface CategoryMapper {
   Category toEntity (CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO toResponse(Category category);
}
