package com.example.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.*;

@Component
public class Person {
    private String name;
    private Parrot parrot;

    @PostConstruct
    public void init() {
        this.name = "Ella";
    }

    @Autowired
    public Person(Parrot parrot){
        this.parrot = parrot;
    }

    @Override
    public String toString(){
        return parrot + " belongs to " + name;
    }
}