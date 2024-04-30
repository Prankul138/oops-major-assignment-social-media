package com.project.socialMedia.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {
    private String commentBody;
    private Long postID;
    private Long userID;

}