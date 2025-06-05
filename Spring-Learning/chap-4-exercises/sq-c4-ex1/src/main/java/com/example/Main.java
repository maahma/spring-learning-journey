package com.example;

import com.example.model.Comment;
import com.example.proxies.EmailCommentNotificationProxy;
import com.example.repositories.DBCommentRepository;
import com.example.services.CommentService;

public class Main {
    public static void main(String[] args) {
        // Create the instance for the dependencies
        var commentRepository = new DBCommentRepository();
        var commentNotificationProxy = new EmailCommentNotificationProxy();

        // Create an instance of the service class and provide the dependencies
        var commentService = new CommentService(commentNotificationProxy, commentRepository);

        // Creates an instance of comment to send as a parameter to the publish comment use case
        var comment = new Comment();
        comment.setAuthor("Maaha");
        comment.setText("Hello World!");

        commentService.publishComment(comment);
    }
}