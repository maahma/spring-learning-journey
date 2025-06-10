package com.example;
import com.example.config.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.components.MessageAppRunner;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    
        var message = context.getBean(MessageAppRunner.class);
        System.out.println(message.run("Hello it's Maaha!"));
    }
}