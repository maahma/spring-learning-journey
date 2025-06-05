package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.*;
import com.example.services.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    
        System.out.println("Before retrieving CommentService");
        var cs = context.getBean(CommentService.class);
        System.out.println("After retrieving CommentService");
    }
}