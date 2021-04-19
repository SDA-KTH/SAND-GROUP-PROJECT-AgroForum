package se.kth.sda.skeleton.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;

@Service
class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    @Autowired
    public CommentService (CommentRepository commentRepository){
        this.commentRepository = commentRepository;

}

public  Comment updateComment(Long id,Comment updateComment) {
    commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    updateComment.setId(id);
    Comment comment = commentRepository.save(updateComment);
    return comment;

}

    public  Comment deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentRepository.delete(comment);
        return comment;
    }

    public  Comment createComment(Long postId,Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        comment.setCommentedPost(post);
        commentRepository.save(comment);
        return comment;
    }




}