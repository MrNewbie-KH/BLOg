package playground.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateArticleRequestDTO {

    private String title;
    private String content;
    private List<Long> categoryIds;
    private List<Long> tagIds;

}
