package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.ProjectConfig;
import com.example.config.*;
import com.example.model.Comment;
import com.example.services.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var service = context.getBean(CommentService.class);
    
        // System.out.println(service.publishComment(new Comment("Hello Maaha here", "Maaha")));
        service.publishComment(new Comment("Hello Maaha here", "Maaha"));
    }
}