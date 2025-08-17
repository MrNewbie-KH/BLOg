package playground.blog.mapper.implementation;

import org.springframework.stereotype.Component;
import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.dto.article.CardArticleResponseDTO;
import playground.blog.dto.article.CreateArticleRequestDTO;
import playground.blog.dto.category.CategoryResponseDTO;
import playground.blog.dto.tag.TagDto;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.*;
import playground.blog.mapper.ArticleMapper;

import java.util.List;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article toEntity(CreateArticleRequestDTO articleRequestDTO, List<Tag>tags , List<Category>categories , List<GroupOfArticles>groups, User user) {
        return
                Article.builder()
                        .title(articleRequestDTO.getTitle())
                        .content(articleRequestDTO.getContent())
                        .tags(tags)
                        .categories(categories)
                        .groups(groups)
                        .author(user)
                        .build();
    }

    @Override
    public ArticleResponseDTO toResponse(Article article, List<TagDto> tagDtos, UserResponseDTO userResponseDTO, List<CategoryResponseDTO>categoryResponseDTOS, List<GroupOfArticles>groupOfArticles, long likeCount, long commentCount) {
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .image(article.getImage())
                .readTime(article.getReadTime())
                .status(article.getStatus())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .authorResponseDTO(userResponseDTO)
                .attachments(article.getAttachments())
                .tagDtos(tagDtos)
                .categoryResponseDTOS(categoryResponseDTOS)
                .groups(groupOfArticles)
                .commentCount(commentCount)
                .likeCount(likeCount)
                .build();
    }

    @Override
    public CardArticleResponseDTO toCardResponse(Article article,String author) {
        return  CardArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .authorName(author)
                .build();
    }
}