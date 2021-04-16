package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.ResourceNotFoundException;

import java.util.List;

/*
    @TODO create the methods needed to implement the API.
    Don't forget to add necessary annotations.
 */
@RestController
public class PostController {

    PostRepository postRepository ;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
      postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);

    }
    @GetMapping("/posts")
    public List<Post> listAllPosts(){
        List<Post> posts= postRepository.findAll();
        return posts;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable long id){
        Post post=postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        postRepository.delete(post);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable long id){
       Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(post);
    }
    @PutMapping("posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody Post updatedPost) {
        postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedPost.setId(id);
        Post post =postRepository.save(updatedPost);
        return  ResponseEntity.ok(post);

    }


}
