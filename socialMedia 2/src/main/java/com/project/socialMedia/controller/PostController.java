package com.project.socialMedia.controller;

import com.project.socialMedia.entity.Post;
import com.project.socialMedia.entity.User;
import com.project.socialMedia.request.EditPostRequest;
import com.project.socialMedia.request.PostRequest;
import com.project.socialMedia.service.PostService;
import com.project.socialMedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostRequest request) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(request.getUserID()));
        if (userOptional.isPresent()) {
            Post post = new Post();
            post.setPostBody(request.getPostBody());
            post.setUserId(request.getUserID());
            post.setDate(new Date());
            postService.createPost(post);
            return ResponseEntity.ok("Post created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @GetMapping()
    public ResponseEntity<?> getPost(@RequestParam Long postID) {
        Optional<Post> postOptional = postService.getPostById(postID);
        if (postOptional.isPresent()) {
            return ResponseEntity.ok(postOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    @PatchMapping()
    public ResponseEntity<String> editPost(@RequestBody EditPostRequest request) {
        Optional<Post> postOptional = postService.getPostById(request.getPostID());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setPostBody(request.getPostBody());
            postService.savePost(post);
            return ResponseEntity.ok("Post edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> deletePost(@RequestParam Long postID) {
        Optional<Post> postOptional = postService.getPostById(postID);
        if (postOptional.isPresent()) {
            postService.deletePost(postID);
            return ResponseEntity.ok("Post deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }
}
