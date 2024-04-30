package com.project.socialMedia.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private Long userId;
    private String name;
    private String email;

}