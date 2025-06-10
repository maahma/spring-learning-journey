package com.example.config;
import org.springframework.context.annotation.*;
import org.aspectj.lang.annotation.*;

@Configuration
@ComponentScan(basePackages = {
    "com.example.services",
    "com.example.logger"
})
@EnableAspectJAutoProxy
public class ProjectConfig {
    
}
