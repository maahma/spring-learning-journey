package com.example.service;

import org.springframework.stereotype.Service;
import com.example.components.*;

@Service
public class MessageService {
    private TimestampProvider timestampProvider;

    public MessageService(TimestampProvider timestampProvider){
        this.timestampProvider = timestampProvider;
    }

    public String sendMessage(String message){
        return "[" + timestampProvider.timeNow() + "]: " + message;    
    }
}
