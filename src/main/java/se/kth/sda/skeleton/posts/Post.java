package se.kth.sda.skeleton.posts;

import se.kth.sda.skeleton.comments.Comment;

import javax.persistence.*;
import java.util.List;

// @TODO add Hibernate annotations
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //the field:

    private Long id;
    private String body;
    private List<Comment> postCommentList;

    //the relationship :


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "commentedPost")
    private List<Comment> comments;
    public Post() {
    }

    public Post(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public List<Comment> getPostCommentsList() {
        return postCommentList;
    }


}
