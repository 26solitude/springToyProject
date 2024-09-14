package toy_project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy_project.demo.entity.Post;
import toy_project.demo.entity.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);

}
