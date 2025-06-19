package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.*;
import com.example.service.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    
        var service1 = context.getBean(CounterService.class);
        var service2 = context.getBean(CounterService.class);

        Boolean b = service1 == service2;
        System.out.println(b);

        System.out.println("Hashcode for service1: " + service1.hashCode());
        System.out.println("Hashcode for service2: " + service2.hashCode());

    }
}