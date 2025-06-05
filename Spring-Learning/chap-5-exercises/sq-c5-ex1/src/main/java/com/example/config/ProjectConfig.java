package com.example.config;
import org.springframework.context.annotation.*;
import com.example.services.*;

@Configuration
public class ProjectConfig {
    @Bean
    public CommentService commentService(){
        return new CommentService();
    }
}