package toy_project.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy_project.demo.entity.Post;
import toy_project.demo.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestParam Long userId,
                                        @RequestParam String imageUrl,
                                        @RequestParam String description) {
        try {
            Post post = postService.createPost(userId, imageUrl, description);
            return ResponseEntity.ok(post);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId){
        Optional<Post> post = postService.getPostByID(postId);
        return post.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                                        @RequestParam Long userId,
                                        @RequestParam String imageUrl,
                                        @RequestParam String description) {
        try {
            Post updatePost = postService.updatePost(postId, userId, imageUrl, description);
            return ResponseEntity.ok(updatePost);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestParam Long userId){
        try{
            postService.deletePost(postId, userId);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {
        List<Post> posts = postService.getPostByUser(userId);
        return ResponseEntity.ok(posts);
    }
}
