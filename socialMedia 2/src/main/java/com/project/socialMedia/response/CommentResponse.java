package com.project.socialMedia.response;

import com.project.socialMedia.entity.CommentCreatorResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponse {
    private Long commentID;
    private String commentBody;
    private CommentCreatorResponse commentCreator;

}