package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.Models.Parrot;

@Configuration
public class ProjectConfig {
    @Bean
    Parrot parrot1() {
        var p = new Parrot();
        p.setName("Riki");
        return p;
    }
}
