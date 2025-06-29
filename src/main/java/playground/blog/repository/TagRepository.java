package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
