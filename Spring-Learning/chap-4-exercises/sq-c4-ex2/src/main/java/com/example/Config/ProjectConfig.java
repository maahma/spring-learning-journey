package com.example.Config;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {
    "com.example.services",
    "com.example.repositories",
    "com.example.proxies"
})
public class ProjectConfig{
    
}