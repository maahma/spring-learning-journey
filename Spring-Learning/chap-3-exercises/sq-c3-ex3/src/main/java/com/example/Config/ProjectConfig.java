package com.example.Config;
import org.springframework.context.annotation.*;
import com.example.Models.*;

// Wiring the beans using the `@Bean` annotated method's parameters
@Configuration
public class ProjectConfig {

    @Bean
    public Parrot parrot(){
        var p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    public Person person(Parrot parrot){
        var p = new Person();
        p.setName("Ella");
        p.setParrot(parrot);
        return p;
    }
}
