package com.project.socialMedia.controller;

import com.project.socialMedia.entity.Post;
import com.project.socialMedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserFeedController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getUserFeed() {
        return postService.getUserFeed();
    }
}
