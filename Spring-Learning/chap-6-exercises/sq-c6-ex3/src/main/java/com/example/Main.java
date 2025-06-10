package com.example;

import java.util.logging.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.services.*;
import com.example.config.ProjectConfig;
import com.example.model.Comment;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var service = context.getBean(CommentService.class);

        // Comment comment = new Comment();
        // comment.setAuthor("Maaha");
        // comment.setComment("Hey Maaha here");

        String value = service.publishComment(new Comment("Hey Maaha here", "Maaha"));

        logger.info(value);
    }
}