package com.example.Models;
import org.springframework.stereotype.*;

@Component
public class Parrot {
    private String name = "Koko";

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Parrot " + name;
    }
}
