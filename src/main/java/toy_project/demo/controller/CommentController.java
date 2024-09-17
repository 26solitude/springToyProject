package toy_project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy_project.demo.entity.Comment;
import toy_project.demo.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestParam Long userId,
                                           @RequestParam Long postId,
                                           @RequestParam String content){
            Comment comment = commentService.createComment(userId, postId, content);
            return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId){
        List<Comment> comments = commentService.getCommentsByPost(postId);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable Long userId){
        List<Comment> comments = commentService.getCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId,
                                                 @RequestParam String content){
        Comment updatedComment = commentService.updateComment(commentId, content);
        return ResponseEntity.ok(updatedComment);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId){
            Comment deletedComment = commentService.deleteComment(commentId);
            return ResponseEntity.ok(deletedComment);
    }
}
