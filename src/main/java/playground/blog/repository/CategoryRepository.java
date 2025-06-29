package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
