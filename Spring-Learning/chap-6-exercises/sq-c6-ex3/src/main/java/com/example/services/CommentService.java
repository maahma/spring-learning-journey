package com.example.services;

import java.util.logging.Logger;
import com.example.model.*;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private Logger logger = Logger.getLogger(CommentService.class.getName());

    public String publishComment(Comment comment){
        logger.info("Publishing comment " + comment.getComment());
        return "SUCCESS";
    }
}
