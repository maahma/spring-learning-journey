package com.example.Config;
import org.springframework.context.annotation.*;
import com.example.Models.*;

// WIRING BEANS USING DIRECT METHOD CALL BETWEEN THE @BEAN METHODS
@Configuration
public class ProjectConfig {
    @Bean
    public Parrot parrot(){
        var p = new Parrot();
        p.setName("Riki");
        return p;
    }

    @Bean
    public Person person(){
        var p = new Person();
        p.setName("Ella");
        p.setParrot(parrot());
        return p;
    }
}
