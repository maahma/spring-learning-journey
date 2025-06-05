package com.example;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.*;
import com.example.services.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    
        var s1 = context.getBean("commentService", CommentService.class);
        var s2 = context.getBean("userService", UserService.class);
    
        boolean b = s1.getCommentRepository() == s2.getCommentRepository();
    
        System.out.println(b);
    }
}