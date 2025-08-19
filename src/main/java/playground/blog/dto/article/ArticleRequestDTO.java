package playground.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import playground.blog.entity.User;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleRequestDTO {
    private String title;
    private String content;
    private String image;
    private String status;
    private List<String> attachments;
}
