package com.example.config;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages={
    "com.example.services",
    "com.example.repositories"
})
public class ProjectConfig {
    
}
