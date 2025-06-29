package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
