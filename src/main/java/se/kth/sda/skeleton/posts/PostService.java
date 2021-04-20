package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.user.User;
import se.kth.sda.skeleton.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/*
    @TODO Implement service methods.
 */
@Service
public class PostService {

    PostRepository postRepository;
    UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    // Updates only user id-defined posts by postOwner only
    public Post updatePost(long id, Post updatedPost, Principal principal) {
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        String userName = principal.getName();
        User user = userService.findUserByEmail(userName);

        if(!userName.equals(post.getPostOwner().getEmail())){
            throw new ResourceNotFoundException();
        }

        updatedPost = post.setUpdatePostValues(updatedPost);
        updatedPost.setId(id);
        updatedPost.setPostOwner(user);

        return updatedPost;
    }

    public Post deletePost(long id, Principal principal) {
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        String userName = principal.getName();

        if(!userName.equals(post.getPostOwner().getEmail())){
            throw new ResourceNotFoundException();
        }

        return post;
    }
}
