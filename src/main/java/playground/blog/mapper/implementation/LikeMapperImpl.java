package playground.blog.mapper.implementation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import playground.blog.dto.like.LikeResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Like;
import playground.blog.entity.User;
import playground.blog.mapper.LikeMapper;

@Component
public class LikeMapperImpl implements LikeMapper {
    @Override
    public Like toEntity(Article article, User user) {
        return Like.builder()
                .article(article)
                .user(user)
                .build();
    }

    @Override
    public LikeResponseDTO toLikeResponseDTO(Long articleId, boolean isLiked, Long countNumberOfLikes) {
       return LikeResponseDTO.builder()
               .articleId(articleId)
               .isLiked(isLiked)
               .likeCount(countNumberOfLikes)
               .build();
    }


}
