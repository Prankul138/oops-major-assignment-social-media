package com.project.socialMedia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.socialMedia.response.CommentResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;
    private String commentBody;

    private CommentCreatorResponse commentCreator;

    @JsonIgnore
    private Long postID;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}

