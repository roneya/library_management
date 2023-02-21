package com.example.LibraryManagement.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue()
    private int id;

    private String name;

    private int age;

    private String country;

    private double rating;

    @OneToMany( mappedBy = "author", cascade = CascadeType.ALL )
    private List<Book> books;

    public Author(int id, String name, int age, String country, double rating, List<Book> books) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
        this.rating = rating;
        this.books = books;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Author(){
        books =  new ArrayList<>();
    }


}
