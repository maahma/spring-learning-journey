package com.example.components;
import org.springframework.stereotype.Component;
import com.example.service.*;

@Component
public class MessageAppRunner {
    private MessageService messageService;

    public MessageAppRunner(MessageService messageService){
        this.messageService = messageService;
    }

    public String run(String message){
        return messageService.sendMessage(message);
    }
}
