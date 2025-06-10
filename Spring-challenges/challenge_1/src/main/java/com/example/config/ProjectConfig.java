package com.example.config;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages={
    "com.example.service",
    "com.example.components"
})
public class ProjectConfig {
    
}
