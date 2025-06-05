package com.example.repositories;
import org.springframework.stereotype.*;
import com.example.model.*;

@Component
public class DBCommentRepository implements CommentRepository{

    @Override
    public void storeComment(Comment comment){
        System.out.println("Storing comment " + comment.getText());
    }
}