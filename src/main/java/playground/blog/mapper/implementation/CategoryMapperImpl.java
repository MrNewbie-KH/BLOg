package playground.blog.mapper.implementation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import playground.blog.dto.category.CategoryRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.entity.Category;
import playground.blog.mapper.CategoryMapper;

@Builder
@AllArgsConstructor
@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public  Category toEntity(CategoryRequestDTO categoryRequestDTO) {
      return   Category.builder()
                .description(categoryRequestDTO.getDescription())
                .name(categoryRequestDTO.getName())
                .build();
    }
    @Override
    public CategoryResponseDTO toResponse(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .description(category.getDescription())
                .name(category.getName())
                .build();
    }

}
