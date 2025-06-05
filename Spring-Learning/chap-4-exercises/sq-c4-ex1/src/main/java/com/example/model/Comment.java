package com.example.model;

public class Comment {
    private String author;
    private String text;

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    @Override
    public String toString(){
        return text + " by " + author;
    }
}
