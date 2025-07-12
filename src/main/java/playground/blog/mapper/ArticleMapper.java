package playground.blog.mapper;

import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Category;
import playground.blog.entity.GroupOfArticles;
import playground.blog.entity.Tag;

import java.util.List;

public interface ArticleMapper {
    Article toEntity(ArticleRequestDTO articleRequestDTO, List<Tag> tags, List<Category>categories, List<GroupOfArticles>groups);
    ArticleResponseDTO  toResponse(Article article);
}
