package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    }
}