package com.example.Models;
import org.springframework.stereotype.Component;
import javax.annotation.*;

@Component
public class Parrot {
    private String name;

    @PostConstruct
    public void init() {
        this.name = "Koko";
    }

    @Override
    public String toString(){
        return "Parrot: " + name;
    }
}
