package com.example.services;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.*;

@Service
@Lazy
public class CommentService {
    public CommentService(){
        System.out.println("CommentService instance created");
    }
}
