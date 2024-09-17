package toy_project.demo.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import toy_project.demo.entity.Comment;
import toy_project.demo.entity.Post;
import toy_project.demo.entity.User;
import toy_project.demo.repository.CommentRepository;
import toy_project.demo.repository.PostRepository;
import toy_project.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    public void testCreateComment_Success() {
        Long userId = 1L;
        Long postId = 1L;
        String content = "Test comment";

        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setId(postId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment createdComment = commentService.createComment(userId, postId, content);

        assertNotNull(createdComment);
        assertEquals(content, createdComment.getContent());
    }

    @Test
    public void testCreateComment_Failure_UserNotFound() {
        Long userId = 1L;
        Long postId = 1L;
        String content = "Test comment";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            commentService.createComment(userId, postId, content);
        });

        assertTrue(exception.getMessage().contains("User not found with id " + userId));
    }

    @Test
    public void testGetCommentsByPost_Success() {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findByPost(post)).thenReturn(List.of(new Comment()));

        List<Comment> comments = commentService.getCommentsByPost(postId);

        assertFalse(comments.isEmpty());
    }

    @Test
    public void testGetCommentsByUser_Success() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(commentRepository.findByUser(user)).thenReturn(List.of(new Comment()));

        List<Comment> comments = commentService.getCommentsByUser(userId);

        assertFalse(comments.isEmpty());
    }

    @Test
    public void testUpdateComment_Success() {
        Long commentId = 1L;
        String newContent = "Updated content";

        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setContent("Old content");

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment updatedComment = commentService.updateComment(commentId, newContent);

        assertEquals(newContent, updatedComment.getContent());
    }

    @Test
    public void testDeleteComment_Success() {
        Long commentId = 1L;

        Comment comment = new Comment();
        comment.setId(commentId);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        Comment deletedComment = commentService.deleteComment(commentId);

        assertEquals(commentId, deletedComment.getId());
        verify(commentRepository, times(1)).delete(comment);
    }
}
