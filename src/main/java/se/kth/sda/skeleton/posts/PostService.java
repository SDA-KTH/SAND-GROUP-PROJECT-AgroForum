package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import se.kth.sda.skeleton.user.UserRepository;
import se.kth.sda.skeleton.user.UserService;

import java.util.List;
import java.util.Optional;

/*
    @TODO Implement service methods.
 */
public class PostService {

    PostRepository postRepository;
    UserService userService;


    @Autowired
    public  PostService (PostRepository postRepository ,  UserService userService){



    }

}
