package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.user.UserRepository;
import se.kth.sda.skeleton.user.UserService;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    PostRepository postRepository;
    UserService userService;


    @Autowired
    public  PostService (PostRepository postRepository ,  UserService userService){



    }
public Post updatePost(Long id , Post updatedPost){
    postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    updatedPost.setId(id);
    Post post =postRepository.save(updatedPost);
return post ;
}

    public Post deletePost(Long id )
    {
        Post post=postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        postRepository.delete(post);
        return post;
    }

    public Post createPost(Post createdPost){
        Post post =postRepository.save(createdPost);
        return post;
    }
    }