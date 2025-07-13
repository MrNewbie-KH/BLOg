package playground.blog.mapper.implementation;

import org.springframework.stereotype.Component;
import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.dto.tag.TagDto;
import playground.blog.entity.*;
import playground.blog.mapper.ArticleMapper;

import java.util.List;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article toEntity(ArticleRequestDTO articleRequestDTO, List<Tag>tags , List<Category>categories , List<GroupOfArticles>groups,User user) {
        return
                Article.builder()
                        .title(articleRequestDTO.getTitle())
                        .content(articleRequestDTO.getContent())
                        .image(articleRequestDTO.getImage())
                        .readTime(articleRequestDTO.getReadTime())
                        .status(articleRequestDTO.getStatus())
                        .tags(tags)
                        .categories(categories)
                        .groups(groups)
                        .author(user)
                        .attachments(articleRequestDTO.getAttachments())
                        .build();
    }

    @Override
    public ArticleResponseDTO toResponse(Article article, List<TagDto> tagDtos, List<CategoryResponseDTO>categoryResponseDTOS,List<GroupOfArticles>groupOfArticles, long likeCount, long commentCount) {
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .image(article.getImage())
                .readTime(article.getReadTime())
                .status(article.getStatus())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .author(article.getAuthor())
                .attachments(article.getAttachments())
                .tagDtos(tagDtos)
                .categoryResponseDTOS(categoryResponseDTOS)
                .groups(groupOfArticles)
                .commentCount(commentCount)
                .likeCount(likeCount)
                .build();
    }
}