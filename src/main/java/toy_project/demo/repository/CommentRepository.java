package toy_project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy_project.demo.entity.Comment;
import toy_project.demo.entity.Post;
import toy_project.demo.entity.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}
