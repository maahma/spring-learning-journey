package com.example.Models;
import org.springframework.stereotype.Component;
import javax.annotation.*;

@Component
public class Parrot {
    private String name;

    @PostConstruct
    public void init(){
        this.name = "Riki";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Parrot: " + name; 
    }
}