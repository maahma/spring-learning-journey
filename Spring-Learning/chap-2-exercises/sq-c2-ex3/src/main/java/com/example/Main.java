package com.example;
import com.example.Models.Parrot;
import com.example.Config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Parrot p1 = context.getBean("parrot1", Parrot.class);
        Parrot p2 = context.getBean("parrot2", Parrot.class);
        Parrot p3 = context.getBean("parrot3", Parrot.class);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // NOT USING A BEAN NAME HERE
        // WILL RETURN PARROT 4 SINCE ITS SET TO PRIMARY
        Parrot p = context.getBean(Parrot.class);
        System.out.println(p);
    }
}