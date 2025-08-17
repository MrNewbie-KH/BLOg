package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.User;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByAuthor(User author);
}
