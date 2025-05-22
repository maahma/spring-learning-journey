package com.example.Models;

public class Person {
    private String name;
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
        return (parrot + " belongs to " + name);
    }
}
