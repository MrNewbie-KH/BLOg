package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
