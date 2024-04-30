package com.project.socialMedia.service;

import com.project.socialMedia.entity.Comment;
import com.project.socialMedia.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
