package com.example;
import com.example.Models.Parrot;
import com.example.Config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        // ADDING A SINGLE BEAN TO THE SPRING CONTEXT USING @BEAN ANNOTATION
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Parrot p = context.getBean(Parrot.class);
        System.out.println(p);

    }
}