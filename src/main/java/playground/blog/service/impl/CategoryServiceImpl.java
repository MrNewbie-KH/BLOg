package playground.blog.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.blog.dto.category.CategoryRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Category;
import playground.blog.exception.custom.DuplicateValueException;
import playground.blog.exception.custom.EntityAlreadyExist;
import playground.blog.exception.custom.NotFoundException;
import playground.blog.exception.custom.NotNullException;
import playground.blog.mapper.CategoryMapper;
import playground.blog.repository.ArticleRepository;
import playground.blog.repository.CategoryRepository;
import playground.blog.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

        private final CategoryRepository categoryRepository;
        private final CategoryMapper categoryMapper;
        private final ArticleRepository articleRepository;

    @Override
        public List<CategoryResponseDTO> getAllCategories(){
    return categoryRepository.findAll().stream()
            .map(category ->categoryMapper.toResponse(category))
            .collect(Collectors.toList());
}
@Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO){
//    check existance
    boolean isAlreadyExist  = categoryRepository.existsByNameIgnoreCase(categoryRequestDTO.getName());
    if(isAlreadyExist){
        throw new EntityAlreadyExist("Category with name " + categoryRequestDTO.getName() + " already exists and can't be duplicated");
    }
    if(categoryRequestDTO.getName()==null || categoryRequestDTO.getName().trim().equals("")){
        throw new NotNullException("Category name can't be null or empty");
    }
    else{
        Category cat =categoryRepository.save(categoryMapper.toEntity(categoryRequestDTO));
        return categoryMapper.toResponse(cat);
    }

}
@Override
      public  CategoryResponseDTO getCategoryById(Long id){
      return categoryRepository.findById(id)
              .map(category -> categoryMapper.toResponse(category))
              .orElseThrow(()->new NotFoundException("Category with id  "+id +" not found"));
}
@Override
public  CategoryResponseDTO getCategoryByName(String name){
    return categoryRepository.findByName(name)
            .map(category -> categoryMapper.toResponse(category))
            .orElseThrow(()->new NotFoundException("Category not found"));
}
        @Override
        public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO){

         Category currentCategory =    categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found"));

            if (!categoryRepository.findByName(categoryRequestDTO.getName()).isEmpty()
                    && !currentCategory.getName().equals(categoryRequestDTO.getName())) {
                throw new DuplicateValueException("Category name already exists");
            }
            currentCategory.setName(categoryRequestDTO.getName());
            currentCategory.setDescription(categoryRequestDTO.getDescription());
            categoryRepository.save(currentCategory);
            return categoryMapper.toResponse(currentCategory);
        }
@Override
       public CategoryResponseDTO deleteCategory(Long id){
    Category currentCategory= categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found"));
    List<Article> articles =  articleRepository.findAllByCategories(currentCategory);
    articles.forEach(article -> {
        article.getCategories().remove(currentCategory);
        if(article.getCategories().isEmpty()){
            Category uncategorized = categoryRepository.findById(1L).orElseThrow(()->new NotFoundException("Category not found"));
            List<Category> categories = new ArrayList<>();
            categories.add(uncategorized);
            article.setCategories(categories);
        }
    });
    articleRepository.saveAll(articles);
    categoryRepository.delete(currentCategory);
    return categoryMapper.toResponse(currentCategory);

}
}
