package playground.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import playground.blog.entity.Category;
import playground.blog.entity.GroupOfArticles;
import playground.blog.entity.Tag;
import playground.blog.entity.User;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String image;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long readTime;
    private String status;
    private List<String> attachments;
    private User author;
    private List<Tag> tags;
    private List<Category> categories;
    private List<GroupOfArticles> groups;
}
