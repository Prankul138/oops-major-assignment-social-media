package com.project.socialMedia.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PostResponse {
    private Long postID;
    private String postBody;
    private Date date;
    private List<CommentResponse> comments;

}
