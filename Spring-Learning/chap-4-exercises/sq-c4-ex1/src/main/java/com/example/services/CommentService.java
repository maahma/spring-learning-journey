package com.example.services;
import com.example.proxies.CommentNotificationProxy;
import com.example.repositories.CommentRepository;
import com.example.model.Comment;

public class CommentService {
    // Define the dependencies as attributes of the class 
    private final CommentNotificationProxy commentNotificationProxy;
    private final CommentRepository commentRepository;

    // Provide the dependencies when the object is built through the parameters of the constructor
    public CommentService(CommentNotificationProxy commentNotificationProxy, CommentRepository commentRepository){
        this.commentNotificationProxy = commentNotificationProxy;
        this.commentRepository = commentRepository;
    }

    // The method that implements the usecase that delegates the store comment and send comment responsibilties to the dependencies
    public void publishComment(Comment comment){
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }
}