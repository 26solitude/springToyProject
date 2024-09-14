package toy_project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toy_project.demo.entity.Post;
import toy_project.demo.entity.User;
import toy_project.demo.repository.PostRepository;
import toy_project.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

    public Post updatePost(Long postId, Long userId, String imageUrl, String description) {
        // 게시물 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + postId));

        // 사용자 유효성 검사
        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to update this post");
        }

        // 수정 내용 적용
        post.setImageUrl(imageUrl);
        post.setDescription(description);

        // 변경된 내용 저장
        return postRepository.save(post);
    }

    public void deletePost(Long postId, Long userId) {
        // 게시물 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + postId));

        // 사용자 유효성 검사
        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to delete");
        }

        // 게시물 삭제
        postRepository.delete(post);
    }

    public List<Post> getPostByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        return postRepository.findByUser(user);
    }

    public Optional<Post> getPostByID(Long postId) {
        return postRepository.findById(postId);
    }
}
