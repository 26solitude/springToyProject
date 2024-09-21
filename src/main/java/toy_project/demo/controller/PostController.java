package toy_project.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import toy_project.demo.dto.PostRequest;
import toy_project.demo.entity.Post;
import toy_project.demo.security.CustomUserDetails;
import toy_project.demo.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest,
                                        Authentication authentication) {
        // Authentication 객체에서 CustomUserDetails 추출
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        // 서비스 계층 호출 시 userId 전달
        Post post = postService.createPost(userId, postRequest.getImageUrl(), postRequest.getDescription());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostByID(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                                        @RequestParam Long userId,
                                        @RequestParam String imageUrl,
                                        @RequestParam String description) {
        Post updatedPost = postService.updatePost(postId, userId, imageUrl, description);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId, @RequestParam Long userId) {
        Post deletedPost = postService.deletePost(postId, userId);
        return ResponseEntity.ok(deletedPost);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {
        List<Post> posts = postService.getPostByUser(userId);
        return ResponseEntity.ok(posts);
    }
}
