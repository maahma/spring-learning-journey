package com.example.model;

public class Comment {
    private String author;
    private String comment;

    public Comment(String comment, String author){
        this.comment = comment;
        this.author = author;
    }

    public void setComment(String comment){
        this.comment = comment;
    }   

    public String getComment(){
        return comment;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }
}
