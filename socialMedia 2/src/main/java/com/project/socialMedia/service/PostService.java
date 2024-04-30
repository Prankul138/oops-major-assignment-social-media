package com.project.socialMedia.service;

import com.project.socialMedia.entity.Comment;
import com.project.socialMedia.entity.Post;
import com.project.socialMedia.repository.PostRepository;
import com.project.socialMedia.entity.CommentCreatorResponse;
import com.project.socialMedia.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getUserFeed() {
        return postRepository.findAllByOrderByDateDesc();
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public void addComment(Comment comment, Post post) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentID(comment.getCommentID());
        commentResponse.setCommentBody(comment.getCommentBody());
        commentResponse.setCommentCreator(new CommentCreatorResponse(comment.getCommentCreator().getUserID(), comment.getCommentCreator().getName()));
        post.getComments().add(comment);
        postRepository.save(post);
    }
}
