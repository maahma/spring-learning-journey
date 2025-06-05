package com.example;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.model.*;
import com.example.services.*;
import com.example.Config.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var comment = new Comment();
        comment.setAuthor("Maaha");
        comment.setText("Demo Comment");

        var commentService = context.getBean(CommentService.class);
        commentService.publishComment(comment);
    }
}