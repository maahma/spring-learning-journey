package com.example.services;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import com.example.model.*;

@Service
public class CommentService {

    private Logger logger = Logger.getLogger(CommentService.class.getName());
    public void publishComment(Comment comment){
        logger.info("Publishing comment: " + comment.getComment());
    }
}
