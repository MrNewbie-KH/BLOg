package playground.blog.dto.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponseDTO {
    Long articleId;
    boolean isLiked;
    Long likeCount;

}
