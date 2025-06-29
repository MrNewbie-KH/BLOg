package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
