package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.services.*;
import com.example.config.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var cs1 = context.getBean("commentService", CommentService.class);
        var cs2 = context.getBean("commentService", CommentService.class);

        boolean b = cs1 == cs2;
        System.out.println(b);
    }
}