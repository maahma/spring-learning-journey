package com.example.proxies;
import com.example.model.*;
import org.springframework.stereotype.*;
 
@Component
public class EmailCommentNotificationProxy implements CommentNotificationProxy{

    @Override
    public void sendComment(Comment comment){
        System.out.println("Sending notification for comment " + comment.getText());
    }
}