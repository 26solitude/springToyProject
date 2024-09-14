package toy_project.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy_project.demo.entity.Post;
import toy_project.demo.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestParam Long userId,
                                        @RequestParam String imageUrl,
                                        @RequestParam String description) {
        try{
            Post post = postService.createPost(userId, imageUrl, description);
            return ResponseEntity.ok(post);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId){
        List<Post> posts =postService.getPostByUser(userId);
        return ResponseEntity.ok(posts);
    }

}
