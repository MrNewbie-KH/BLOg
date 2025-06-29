package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.GroupOfArticles;

public interface GroupOfArticlesRepository extends JpaRepository<GroupOfArticles, Long> {
}
