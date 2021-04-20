package se.kth.sda.skeleton.posts;

import se.kth.sda.skeleton.user.User;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.xml.stream.events.Comment;

import java.util.Date;
import java.util.List;

// @TODO add Hibernate annotations
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //the field:
    private String title;
    private String body;
    private Date created;
    private Date updated;

    //the relationship :
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "email")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(nullable = false)
    @NotNull
    private User postOwner;

    @OneToMany(mappedBy = "commentOwner")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comments;

    @PrePersist
    protected void onCreate(){
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updated = new Date();
    }

    public Post() {
    }

    public Post(String body) {
        this.body = body;
    }

    public Post setUpdatePostValues(Post updatedPost){
        if (updatedPost.getBody() == null){
            updatedPost.setBody(this.getBody());
        }
        if (updatedPost.getTitle() == null) {
            updatedPost.setTitle(this.getTitle());
        }
        return updatedPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(User postOwner) {
        this.postOwner = postOwner;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
