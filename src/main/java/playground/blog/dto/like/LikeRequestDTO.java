package playground.blog.dto.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LikeRequestDTO {
    Long articleId;
}
