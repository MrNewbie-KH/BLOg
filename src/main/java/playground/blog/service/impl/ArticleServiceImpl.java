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
import playground.blog.exception.custom.NotAllowedException;
import playground.blog.exception.custom.NotFoundException;
import playground.blog.exception.custom.NotNullException;
import playground.blog.mapper.ArticleMapper;
import playground.blog.mapper.CategoryMapper;
import playground.blog.mapper.TagMapper;
import playground.blog.mapper.UserMapper;
import playground.blog.repository.*;
import playground.blog.service.ArticleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final GroupOfArticlesRepository groupOfArticlesRepository;
    private final LikeRepository likeRepository;

    //    -------------------------------------------------------
    public ArticleResponseDTO createArticle(CreateArticleRequestDTO requestDTO) {
//        you get request body + get the user from token
//        first extract user
        Authentication authenticationObject = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authenticationObject.getName();
        User currentUser = userRepository.findByEmail(userEmail).orElseThrow(() -> new NotFoundException("User not found"));
        UserResponseDTO userResponseDTO = userMapper.toResponse(currentUser);
//        -----------------------------------------------------------------------------------
//        tags----------------------
        List<Tag> tags = tagRepository.findAllById(requestDTO.getTagIds());
        List<TagDto> tagResponseList = tags.stream().map(tag -> tagMapper.toResponse(tag)).toList();
//        categories----------------
        if (requestDTO.getCategoryIds() == null || requestDTO.getCategoryIds().isEmpty()) {
            requestDTO.setCategoryIds(List.of(1L)); // default category
        }

        List<Category> categories = categoryRepository.findAllById(requestDTO.getCategoryIds());
        List<CategoryResponseDTO> categoryResponseList = categories.stream().map(category -> categoryMapper.toResponse(category)).toList();
        Article savedArticle = articleRepository.save(articleMapper.toEntity(requestDTO, tags, categories, null, currentUser));
        return articleMapper.toResponse(savedArticle, tagResponseList, userResponseDTO, categoryResponseList, null, 0, 0);

    }

    //    --------------------------------------------------------
    public List<CardArticleResponseDTO> findAllMyArticles() {
        Authentication authObject = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authObject.getName();
        User currentUser = userRepository.findByEmail(userEmail).orElseThrow(() -> new NotFoundException("User not found"));
        List<Article> myArticles = articleRepository.findAllByAuthor(currentUser);
        return myArticles.stream().map(article -> articleMapper.toCardResponse(article, currentUser.getFirstName())).toList();

    }

    public ArticleResponseDTO findArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
        List<Tag> tags = article.getTags();
        List<TagDto> tagDTOList = tags.stream().map(tag -> tagMapper.toResponse(tag)).toList();
        List<Category> categories = article.getCategories();
        List<CategoryResponseDTO> categoryResponseDTOS = categories.stream().map(category -> categoryMapper.toResponse(category)).toList();
        User authorOfTheArticle = article.getAuthor();
        UserResponseDTO userResponseDTO = userMapper.toResponse(authorOfTheArticle);
        long likes = likeRepository.countByArticleId(id);
        return articleMapper.toResponse(article, tagDTOList, userResponseDTO, categoryResponseDTOS, null, likes, 0);
    }

    @Override
    public List<CardArticleResponseDTO> findArticlesByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        List<Article> articles = articleRepository.findAllByCategories(category);

        return articles.stream().map(article -> articleMapper.toCardResponse(article, article.getAuthor().getFirstName())).toList();


    }

    @Override
    public List<CardArticleResponseDTO> findArticlesByAuthorId(Long id) {
        User author = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        List<Article> articles = articleRepository.findAllByAuthor(author);

        return articles.stream().map(article -> articleMapper.toCardResponse(article, article.getAuthor().getFirstName())).toList();

    }

    @Override
    public ArticleResponseDTO addCategoryToArticle(Long id, List<Long> categoriesId) {
//        check the user is the one created the post first
        Authentication authObject = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authObject.getName();
//        look whether the post id is a valid one or not
        Article currentArticle = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
        if (!Objects.equals(currentArticle.getAuthor().getEmail(), userEmail)) {
            throw new NotAllowedException("This article doesn't belong to you, you aren't allowed to modify it");
        }
//        add categories to it and if it only has uncategorized then remove it first
        List<Category> categories = categoryRepository.findAllById(categoriesId);
        Category uncategorized = categoryRepository.findById(1L).orElseThrow(() -> new NotFoundException("Uncategorized not found"));
        currentArticle.getCategories().addAll(categories);
        currentArticle.getCategories().remove(uncategorized);
        Article finalArticle = articleRepository.save(currentArticle);
        List<TagDto> tagDtos = finalArticle.getTags().stream().map(tag -> tagMapper.toResponse(tag)).toList();
        List<CategoryResponseDTO> categoryResponseDTOS = finalArticle.getCategories().stream().map(category -> categoryMapper.toResponse(category)).toList();
        UserResponseDTO currentUser = userMapper.toResponse(currentArticle.getAuthor());
        return articleMapper.toResponse(finalArticle, tagDtos, currentUser, categoryResponseDTOS, null, 0, 0);

    }

    @Override
    public ArticleResponseDTO removeCategoriesFromArticle(Long id, List<Long> categoriesId) {
//        check the user is the one created the post first
        Authentication authObject = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authObject.getName();
//        look whether the post id is a valid one or not
        Article currentArticle = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
        if (!Objects.equals(currentArticle.getAuthor().getEmail(), userEmail)) {
            throw new NotAllowedException("This article doesn't belong to you, you aren't allowed to modify it");
        }
//        add categories to it and if it only has uncategorized then remove it first
        List<Category> categories = categoryRepository.findAllById(categoriesId);
        Category uncategorized = categoryRepository.findById(1L).orElseThrow(() -> new NotFoundException("Uncategorized not found"));
        currentArticle.getCategories().removeAll(categories);
        if (currentArticle.getCategories().isEmpty())
            currentArticle.getCategories().add(uncategorized);
        Article finalArticle = articleRepository.save(currentArticle);
        List<TagDto> tagDtos = finalArticle.getTags().stream().map(tag -> tagMapper.toResponse(tag)).toList();
        List<CategoryResponseDTO> categoryResponseDTOS = finalArticle.getCategories().stream().map(category -> categoryMapper.toResponse(category)).toList();
        UserResponseDTO currentUser = userMapper.toResponse(currentArticle.getAuthor());
        Long likes = likeRepository.countByArticleId(finalArticle.getId());
        return articleMapper.toResponse(finalArticle, tagDtos, currentUser, categoryResponseDTOS, null, likes, 0);

    }


//
//    @Override
//    public ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO requestDTO) {
//        Authentication authObject = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authObject.getName();
//        Article currentArticle = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
//        if (!Objects.equals(currentArticle.getAuthor().getEmail(), userEmail)) {
//            throw new NotAllowedException("You aren't allowed to perform any post changes");
//        }
//        if (!requestDTO.getTitle().isEmpty())
//            currentArticle.setTitle(requestDTO.getTitle());
//        if (!requestDTO.getContent().isEmpty())
//            currentArticle.setContent(requestDTO.getContent());
//        if (!requestDTO.getImage().isEmpty())
//            currentArticle.setImage(requestDTO.getImage());
//        if (!requestDTO.getStatus().isEmpty())
//            currentArticle.setStatus(requestDTO.getStatus());
//        if (!requestDTO.getAttachments().isEmpty())
//            currentArticle.setAttachments(requestDTO.getAttachments());
//        articleRepository.save(currentArticle);
//        List<TagDto> tagDtos = currentArticle.getTags().stream().map(tag -> tagMapper.toResponse(tag)).toList();
//        List<CategoryResponseDTO> categoryResponseDTOS = currentArticle.getCategories().stream().map(category -> categoryMapper.toResponse(category)).toList();
//        UserResponseDTO currentUser = userMapper.toResponse(currentArticle.getAuthor());
//        return articleMapper.toResponse(currentArticle,tagDtos,currentUser,categoryResponseDTOS,null,0,0);
//
//    }
}

