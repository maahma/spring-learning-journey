package com.example.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repositories.CommentRepository;

@Service
public class UserService {
    @Autowired
    private CommentRepository commentRepository;

    public UserService(){
        System.out.println("UserService instance created");
    }

    public CommentRepository getCommentRepository(){
        return commentRepository;
    }
}
