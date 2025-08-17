package playground.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String authorName;


}
