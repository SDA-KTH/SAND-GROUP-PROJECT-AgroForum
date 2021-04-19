package se.kth.sda.skeleton.comments;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;

import java.util.List;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    PostRepository postRepository;
    CommentService  commentService;


    @Autowired
    public CommentController(CommentRepository commentRepository, PostRepository postRepository,CommentService  commentService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId,@RequestBody Comment createcomment){
        Comment comment = commentService.createComment(postId,createcomment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);

    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getPostCommentsList(@PathVariable Long postId){
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(post.getPostCommentList());
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment updateComment){
        Comment comment = commentService.updateComment(id,updateComment);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id){
      Comment comment = commentService.deleteComment(id);
      return ResponseEntity.ok(comment);
    }


}