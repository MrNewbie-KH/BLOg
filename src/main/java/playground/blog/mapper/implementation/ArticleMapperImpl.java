package playground.blog.mapper.implementation;

import org.springframework.stereotype.Component;
import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Category;
import playground.blog.entity.GroupOfArticles;
import playground.blog.entity.Tag;
import playground.blog.mapper.ArticleMapper;

import java.util.List;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article toEntity(ArticleRequestDTO articleRequestDTO, List<Tag>tags , List<Category>categories , List<GroupOfArticles>groups) {
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
                        .author(articleRequestDTO.getAuthor())
                        .attachments(articleRequestDTO.getAttachments())
                        .build();
    }

    @Override
    public ArticleResponseDTO toResponse(Article article) {
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
                .tags(article.getTags())
                .categories(article.getCategories())
                .groups(article.getGroups())
                .build();
    }
}
/*

    private List<String> attachments;
    private String authorName;
    private List<String> tagNames;
    private List<String> categoryNames;
    private List<String> groupNames;
 */