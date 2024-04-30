package com.project.socialMedia.controller;

import com.project.socialMedia.entity.Comment;
import com.project.socialMedia.entity.Post;
import com.project.socialMedia.entity.User;
import com.project.socialMedia.request.CreateCommentRequest;
import com.project.socialMedia.request.EditCommentRequest;
import com.project.socialMedia.entity.CommentCreatorResponse;
import com.project.socialMedia.response.CommentResponse;
import com.project.socialMedia.service.CommentService;
import com.project.socialMedia.service.PostService;
import com.project.socialMedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CreateCommentRequest request) {
        Optional<Post> postOptional = postService.getPostById(request.getPostID());
        if (!postOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }

        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(request.getUserID()));
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }

        Comment comment = new Comment();
        comment.setCommentBody(request.getCommentBody());
        comment.setCommentCreator(new CommentCreatorResponse(userOptional.get().getId(), userOptional.get().getName()));
        comment.setPostID(request.getPostID());
        comment.setPost(postOptional.get());
        commentService.createComment(comment);
        postService.addComment(comment, postOptional.get());

        return ResponseEntity.ok("Comment created successfully");
    }

    @GetMapping()
    public ResponseEntity<?> getComment(@RequestParam Long commentID) {
        Optional<Comment> commentOptional = commentService.getCommentById(commentID);
        if (!commentOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
        Comment comment = commentOptional.get();
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentID(comment.getCommentID());
        commentResponse.setCommentBody(comment.getCommentBody());
        commentResponse.setCommentCreator(new CommentCreatorResponse(comment.getCommentCreator().getUserID(), comment.getCommentCreator().getName()));

        return ResponseEntity.ok(commentOptional.get());
    }

    @PatchMapping()
    public ResponseEntity<String> editComment(@RequestBody EditCommentRequest request) {
        Optional<Comment> commentOptional = commentService.getCommentById(request.getCommentID());
        if (!commentOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
        Comment comment = commentOptional.get();
        comment.setCommentBody(request.getCommentBody());
        commentService.saveComment(comment);
        return ResponseEntity.ok("Comment edited successfully");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteComment(@RequestParam Long commentID) {
        Optional<Comment> commentOptional = commentService.getCommentById(commentID);
        if (!commentOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
        commentService.deleteComment(commentID);
        return ResponseEntity.ok("Comment deleted");
    }
}
