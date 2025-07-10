package playground.blog.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.blog.dto.category.CategoryRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.entity.Category;
import playground.blog.mapper.CategoryMapper;
import playground.blog.repository.CategoryRepository;
import playground.blog.service.CategoryService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

        private final CategoryRepository categoryRepository;
        private final CategoryMapper categoryMapper;

@Override
        public List<CategoryResponseDTO> getAllCategories(){
    return categoryRepository.findAll().stream()
            .map(category ->categoryMapper.toResponse(category))
            .collect(Collectors.toList());
}
@Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO){
    Category cat =categoryRepository.save(categoryMapper.toEntity(categoryRequestDTO));
    return categoryMapper.toResponse(cat);
}
@Override
      public  CategoryResponseDTO getCategoryById(Long id){
      return categoryRepository.findById(id)
              .map(category -> categoryMapper.toResponse(category))
              .orElseThrow(()->new EntityNotFoundException("Category not found"));
}
@Override
public  CategoryResponseDTO getCategoryByName(String name){
    return categoryRepository.findByName(name)
            .map(category -> categoryMapper.toResponse(category))
            .orElseThrow(()->new EntityNotFoundException("Category not found"));
}
        @Override
        public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO){
    return
            categoryRepository.findById(id)
                    .map(category -> categoryMapper.toResponse(category))
                    .orElseThrow(()->new EntityNotFoundException("Category not found"));
}
@Override
       public CategoryResponseDTO deleteCategory(Long id){
    Optional<Category> cat = categoryRepository.findById(id);
    if(cat.isPresent()){
        categoryRepository.deleteById(id);
        return categoryMapper.toResponse(cat.get());
    }
    throw new EntityNotFoundException("Category not found");
}
}
