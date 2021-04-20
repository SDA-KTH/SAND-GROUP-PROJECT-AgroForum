package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.comments.CommentRepository;
import se.kth.sda.skeleton.user.User;
import se.kth.sda.skeleton.user.UserService;

import java.security.Principal;
import java.util.List;

/*
    @TODO create the methods needed to implement the API.
    Don't forget to add necessary annotations.
 */
@RequestMapping("/posts")
@RestController
public class PostController {

    PostRepository postRepository;
    PostService postService;
    UserService userService;
    CommentRepository commentRepository;

    @Autowired
    public PostController(PostRepository postRepository,
                          PostService postService,
                          UserService userService) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.userService = userService;
    }
    // Creates new post by User
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, Principal principal){
        String useName = principal.getName();
        User user = userService.findUserByEmail(useName);
        post.setPostOwner(user);
        postRepository.save(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    // Returns all posts
    @GetMapping
    public List<Post> listAllPosts(){
        return postRepository.findAll();
    }

    // Returns an id-defined post
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable long id){
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(post);
    }

    // Updates an id-defined post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody Post updatedPost,
                                           Principal principal) {
        Post post = postService.updatePost(id, updatedPost, principal);
        return  ResponseEntity.ok(post);
    }

    // Deletes an id-defined post
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long id, Principal principal){
        Post post = postService.deletePost(id, principal);
        postRepository.delete(post);
    }








}
