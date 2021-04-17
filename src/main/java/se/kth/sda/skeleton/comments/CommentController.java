package se.kth.sda.skeleton.comments;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    PostRepository postRepository;

    @Autowired

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @Validated @RequestBody Comment comment){
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        comment.setCommentedPost(post);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);

    }
}