package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.blog.entity.GroupOfArticles;
import playground.blog.entity.User;

import java.util.List;

public interface GroupOfArticlesRepository extends JpaRepository<GroupOfArticles, Long> {
    List<GroupOfArticles> findByUser(User user);
}
