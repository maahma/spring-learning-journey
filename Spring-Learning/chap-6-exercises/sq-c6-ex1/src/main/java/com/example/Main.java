package com.example;
import com.example.config.*;
import com.example.model.Comment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.services.CommentService;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        
        var service = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setComment("Hello");
        comment.setAuthor("Maaha");

        service.publishComment(comment);
    }
}