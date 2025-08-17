package playground.blog.mapper;

import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.dto.article.CardArticleResponseDTO;
import playground.blog.dto.article.CreateArticleRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.dto.tag.TagDto;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.*;

import java.util.List;

public interface ArticleMapper {
    Article toEntity(CreateArticleRequestDTO articleRequestDTO, List<Tag> tags, List<Category>categories, List<GroupOfArticles>groups, User user);
    ArticleResponseDTO  toResponse(Article article, List<TagDto> tagDtos, UserResponseDTO userResponseDTO, List<CategoryResponseDTO>categoryResponseDTOS, List<GroupOfArticles>groupOfArticles, long likeCount, long commentCount);
    CardArticleResponseDTO toCardResponse(Article article,String name);
}
