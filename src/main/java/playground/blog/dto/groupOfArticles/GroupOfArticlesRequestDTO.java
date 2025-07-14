package playground.blog.dto.groupOfArticles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupOfArticlesRequestDTO {
    String title;
    String description;
    Long userId;
}
