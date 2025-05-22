package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.Config.*;
import com.example.Models.*;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Parrot parrot = context.getBean(Parrot.class);
        Person person = context.getBean(Person.class);

        System.out.println(parrot);
        System.out.println(person);
    }
}