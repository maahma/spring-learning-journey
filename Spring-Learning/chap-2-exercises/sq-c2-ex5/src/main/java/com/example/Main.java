package com.example;
import com.example.Models.Parrot;
import com.example.Config.ProjectConfig;
import org.springframework.context.annotation.*;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot x = new Parrot();
        x.setName("Riki");
        Supplier<Parrot> parrotSupplier = () -> x;

        context.registerBean("parrot1", Parrot.class, parrotSupplier);

        Parrot p = context.getBean(Parrot.class);
        System.out.println(p);
    }
}