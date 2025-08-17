package playground.blog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.dto.article.CardArticleResponseDTO;
import playground.blog.dto.article.CreateArticleRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.dto.tag.TagDto;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.*;
import playground.blog.exception.custom.NotFoundException;
import playground.blog.mapper.ArticleMapper;
import playground.blog.mapper.CategoryMapper;
import playground.blog.mapper.TagMapper;
import playground.blog.mapper.UserMapper;
import playground.blog.repository.*;
import playground.blog.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final TagMapper tagMapper;
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;
//    to be added
//    private final GroupOfArticlesMapper groupOfArticlesMapper;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final GroupOfArticlesRepository  groupOfArticlesRepository;

//    -------------------------------------------------------
    public ArticleResponseDTO createArticle(CreateArticleRequestDTO requestDTO) {
//        you get request body + get the user from token
//        first extract user
        Authentication authenticationObject = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authenticationObject.getName();
        User currentUser = userRepository.findByEmail(userEmail).orElseThrow(()-> new NotFoundException("User not found"));
        UserResponseDTO userResponseDTO = userMapper.toResponse(currentUser);
//        -----------------------------------------------------------------------------------
//        tags----------------------
        List<Tag>tags = tagRepository.findAllById(requestDTO.getTagIds());
        List<TagDto> tagResponseList = tags.stream().map(tag->tagMapper.toResponse(tag)).toList();
//        categories----------------
        if (requestDTO.getCategoryIds() == null || requestDTO.getCategoryIds().isEmpty()) {
            requestDTO.setCategoryIds(List.of(1L)); // default category
        }

        List<Category> categories = categoryRepository.findAllById(requestDTO.getCategoryIds());
        List<CategoryResponseDTO> categoryResponseList = categories.stream().map(category->categoryMapper.toResponse(category)).toList();
       Article savedArticle =  articleRepository.save(articleMapper.toEntity(requestDTO, tags, categories,null,currentUser));
       return articleMapper.toResponse(savedArticle,tagResponseList,userResponseDTO,categoryResponseList,null,0,0);

    }
//    --------------------------------------------------------
    public List<CardArticleResponseDTO> findAllMyArticles(){
        Authentication authObject = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authObject.getName();
        User currentUser = userRepository.findByEmail(userEmail).orElseThrow(()-> new NotFoundException("User not found"));
       List<Article> myArticles =  articleRepository.findAllByAuthor(currentUser);
       return myArticles.stream().map(article -> articleMapper.toCardResponse(article,currentUser.getFirstName())).toList();

    }
    public ArticleResponseDTO findArticleById(Long id){
       Article article =  articleRepository.findById(id).orElseThrow(()-> new NotFoundException("Article not found"));
       List<Tag> tags = article.getTags();
       List<TagDto> tagDTOList = tags.stream().map(tag->tagMapper.toResponse(tag)).toList();
       List<Category> categories = article.getCategories();
        List<CategoryResponseDTO> categoryResponseDTOS = categories.stream().map(category->categoryMapper.toResponse(category)).toList();
        User authorOfTheArticle = article.getAuthor();
        UserResponseDTO userResponseDTO = userMapper.toResponse(authorOfTheArticle);
       return articleMapper.toResponse(article,tagDTOList,userResponseDTO,categoryResponseDTOS,null,0,0);
    }

    @Override
    public List<CardArticleResponseDTO> findArticlesByCategory(Long id) {
        Category category =categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("Category not found"));
        List<Article> articles = articleRepository.findAllByCategories(category);

            return articles.stream().map(article -> articleMapper.toCardResponse(article,article.getAuthor().getFirstName())).toList();


    }

}
