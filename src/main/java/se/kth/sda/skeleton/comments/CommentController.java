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

    @Autowired
    public CommentController(CommentRepository commentRepository, PostRepository articleRepository){
        this.postRepository = articleRepository;
        this.commentRepository = commentRepository;
    }


  @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @Validated @RequestBody Comment comment){
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        comment.setCommentedPost(post);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);

    }


    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<Comment>> getPostCommentsList(@PathVariable Long postId){
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(post.getPostCommentsList());
    }


    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable Long id){
        commentRepository.deleteById(id);
    }


    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Validated @RequestBody Comment commentParams){
        Comment existingComment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentParams.setId(id);
        commentRepository.save(commentParams);
        return ResponseEntity.ok(commentParams);
    }

}