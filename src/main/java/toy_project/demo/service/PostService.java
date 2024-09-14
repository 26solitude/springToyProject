package toy_project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toy_project.demo.entity.Post;
import toy_project.demo.entity.User;
import toy_project.demo.repository.PostRepository;
import toy_project.demo.repository.UserRepository;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Post createPost(Long userId, String imageUrl, String description) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Post post = new Post();
        post.setUser(user);
        post.setImageUrl(imageUrl);
        post.setDescription(description);
        return postRepository.save(post);
    }

    public List<Post> getPostByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        return postRepository.findByUser(user);
    }


}
