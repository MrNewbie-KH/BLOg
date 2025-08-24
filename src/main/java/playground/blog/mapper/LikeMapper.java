package playground.blog.mapper;

import playground.blog.dto.like.LikeResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Like;
import playground.blog.entity.User;

public interface LikeMapper {
    Like toEntity (Article article, User user);
    LikeResponseDTO toLikeResponseDTO (Long ArticleId,boolean isLiked , Long countNumberOfLikes);
}
