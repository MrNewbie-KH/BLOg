package playground.blog.service;

import playground.blog.dto.like.LikeResponseDTO;
import playground.blog.entity.Like;

public interface LikeService {
    public LikeResponseDTO toggleLike (Long id);

}
