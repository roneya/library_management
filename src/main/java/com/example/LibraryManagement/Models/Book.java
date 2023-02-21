package com.example.LibraryManagement.Models;

import com.example.LibraryManagement.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int pages;
    private boolean issued;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    public Book() {}

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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transactions> listOfTransactions = new ArrayList<>();

    public List<Transactions> getListOfTransactions() {
        return listOfTransactions;
    }

    public void setListOfTransactions(List<Transactions> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }

    @ManyToOne
    @JoinColumn //Author id (parent key)
    private Author author; //parent



    public Author getAuthor() {
        return author;
    }


    @ManyToOne
    @JoinColumn
    private Card card;



    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
