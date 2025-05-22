package com.example;
import com.example.Config.ProjectConfig;
import org.springframework.context.annotation.*;
import com.example.Models.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean(Person.class);
        Parrot parrot = context.getBean(Parrot.class);
        
        System.out.println(parrot);
        System.out.println(person);        
    }
}