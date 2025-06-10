package com.example.config;
import org.springframework.context.annotation.*;
import org.springframework.aop.aspectj.*;

@Configuration
@ComponentScan(basePackages = {
    "com.example.services",
    "com.example.logger"
})
@EnableAspectJAutoProxy
public class ProjectConfig {
    
}
