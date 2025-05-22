package com.example.Models;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Component
public class Person {
    private String name = "Ella";

    @Autowired
    private Parrot parrot;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public void setParrot(Parrot parrot){
        this.parrot = parrot;
    }

    public Parrot getParrot(){
        return parrot;
    }

    @Override
    public String toString(){
        return parrot + " belongs to " + name;
    }
}