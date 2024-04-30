package com.project.socialMedia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.socialMedia.response.CommentResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("postID")
    private Long id;

    @Column(nullable = false)
    private String postBody;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Date getDate() {
        return date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("date")
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
