package toy_project.demo.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import toy_project.demo.entity.Post;
import toy_project.demo.entity.User;
import toy_project.demo.repository.PostRepository;
import toy_project.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    public PostServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost() {
        Long userId = 1L;
        String imageUrl = "http://example.com/image.png";
        String description = "This is a test post.";

        User user = new User("testuser", "Test User", "test@example.com", "password");
        user.setId(userId);

        Post post = new Post(user, imageUrl, description);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post createdPost = postService.createPost(userId, imageUrl, description);

        assertNotNull(createdPost);
        assertEquals(userId, createdPost.getUser().getId());
        assertEquals(imageUrl, createdPost.getImageUrl());
        assertEquals(description, createdPost.getDescription());

        verify(userRepository, times(1)).findById(userId);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void testUpdatePost() {
        // Arrange
        Long postId = 1L;
        Long userId = 1L;

        // Create a User object with all required fields
        User user = new User("test_user", "Test User", "test.user@example.com", "testpassword");
        user.setId(userId);

        // Create a Post object with all required fields
        Post post = new Post();
        post.setId(postId);
        post.setUser(user);
        post.setImageUrl("oldImageUrl");
        post.setDescription("oldDescription");

        // Mocking repository methods
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        // Act
        Post updatedPost = postService.updatePost(postId, userId, "newImageUrl", "newDescription");

        // Assert
        assertNotNull(updatedPost);
        assertEquals("newImageUrl", updatedPost.getImageUrl());
        assertEquals("newDescription", updatedPost.getDescription());
        verify(postRepository, times(1)).findById(postId);  // postRepository 사용으로 변경
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void testDeletePost() {
        // Arrange
        Long postId = 1L;
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Post post = new Post();
        post.setUser(user);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        postService.deletePost(postId, userId);

        // Assert
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).delete(post);
    }

    @Test
    public void testGetPostsByUser() {
        Long userId = 1L;
        User user = new User("testuser", "Test User", "test@example.com", "password");
        user.setId(userId);

        Post post1 = new Post(user, "http://example.com/image1.png", "Description 1");
        Post post2 = new Post(user, "http://example.com/image2.png", "Description 2");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findByUser(user)).thenReturn(List.of(post1, post2));

        List<Post> posts = postService.getPostByUser(userId);

        assertEquals(2, posts.size());
        assertEquals("Description 1", posts.get(0).getDescription());
        assertEquals("Description 2", posts.get(1).getDescription());

        verify(userRepository, times(1)).findById(userId);
        verify(postRepository, times(1)).findByUser(user);
    }
}