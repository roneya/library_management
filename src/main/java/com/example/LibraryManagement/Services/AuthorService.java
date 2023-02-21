package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.DTOs.AuthorEntryDto;
import com.example.LibraryManagement.Models.Author;
import com.example.LibraryManagement.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        Author author = new Author();
        
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);
        return "Author Added";
    }
}
