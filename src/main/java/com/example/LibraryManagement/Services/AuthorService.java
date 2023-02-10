package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.Models.Author;
import com.example.LibraryManagement.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(Author author){
        authorRepository.save(author);
        return "Author Added";
    }
}
