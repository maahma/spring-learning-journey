package com.example.Config;
import org.springframework.context.annotation.*;
import com.example.Models.Parrot;

@Configuration
public class ProjectConfig {
    @Bean
    Parrot parrot1() {
        var p = new Parrot();
        p.setName("Riki");
        return p;
    }

    @Bean
    Parrot parrot2() {
        var p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean 
    Parrot parrot3() {
        var p = new Parrot();
        p.setName("Mini");
        return p;
    }

    @Primary
    @Bean
    Parrot parrot4() {
        var p = new Parrot();
        p.setName("Lola");
        return p;
    }
}
