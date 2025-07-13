package playground.blog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.dto.tag.TagDto;
import playground.blog.entity.*;
import playground.blog.mapper.ArticleMapper;
import playground.blog.mapper.CategoryMapper;
import playground.blog.mapper.TagMapper;
import playground.blog.repository.*;
import playground.blog.service.ArticleService;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final TagMapper tagMapper;
    private final CategoryMapper categoryMapper;
//    to be added
//    private final GroupOfArticlesMapper groupOfArticlesMapper;

    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final GroupOfArticlesRepository  groupOfArticlesRepository;

//    -------------------------------------------------------
    public ArticleResponseDTO createArticle(ArticleRequestDTO articleRequestDTO) {
        List<Tag>tags = tagRepository.findAllById(articleRequestDTO.getTagIds());
        List<TagDto> tagResponseList = tags.stream().map(tag->tagMapper.toResponse(tag)).toList();
        List<Category> categories = categoryRepository.findAllById(articleRequestDTO.getCategoryIds());
        List<CategoryResponseDTO> categoryResponseList = categories.stream().map(category->categoryMapper.toResponse(category)).toList();
        List<GroupOfArticles>groups = groupOfArticlesRepository.findAllById(articleRequestDTO.getGroupIds());
        User user = userRepository.findById(articleRequestDTO.getAuthorId()).orElse(null);
       Article savedArticle =  articleRepository.save(articleMapper.toEntity(articleRequestDTO, tags, categories, groups,user));
       return articleMapper.toResponse(savedArticle,tagResponseList,categoryResponseList,null,0,0);

    }
//    --------------------------------------------------------

}
