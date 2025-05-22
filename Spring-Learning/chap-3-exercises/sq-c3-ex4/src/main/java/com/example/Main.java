package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.Config.*;
import com.example.Models.*;

// USING AUTOWIRED FIELD INJECTION TO LINK THE 2 BEANS 
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}