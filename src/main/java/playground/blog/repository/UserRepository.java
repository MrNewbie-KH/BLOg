package playground.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.blog.entity.User;

public interface UserRepository  extends JpaRepository<User,Long> {
}
