package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional <Like >findByUserIdAndArticleId(Long userId, Long articleId);
    long countByArticleId(Long articleId);

}
