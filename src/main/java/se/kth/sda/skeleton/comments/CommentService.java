package se.kth.sda.skeleton.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.user.User;
import se.kth.sda.skeleton.user.UserService;

import java.security.Principal;

@Service
public class CommentService {

    CommentRepository commentRepository;
    UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    // Updates only user id-defined comments by userCommentOwner only
    public Comment updateComment(long id, Comment updatedComment, Principal principal) {
        Comment comment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        String userName = principal.getName();
        User user = userService.findUserByEmail(userName);

        if(!userName.equals(comment.getUserCommentOwner().getEmail())){
            throw new ResourceNotFoundException();
        }

        updatedComment.setId(id);
        updatedComment.setCommentOwner(comment.getCommentOwner());
        updatedComment.setUserCommentOwner(user);
        commentRepository.save(updatedComment);

        return updatedComment;
    }

    public Comment deleteComment(long id, Principal principal) {
        Comment comment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        String userName = principal.getName();

        if(!userName.equals(comment.getUserCommentOwner().getEmail())){
            throw new ResourceNotFoundException();
        }

        return comment;
    }
}