package com.example;
import com.example.Config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.Models.Parrot;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Parrot p = context.getBean(Parrot.class);
        System.out.println(p);
    }
}