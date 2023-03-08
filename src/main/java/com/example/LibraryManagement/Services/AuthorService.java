package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.DTOs.AuthorEntryDto;
import com.example.LibraryManagement.DTOs.AuthorResponseDto;
import com.example.LibraryManagement.DTOs.BookResponseDto;
import com.example.LibraryManagement.Models.Author;
import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Repositories.AuthorRepository;
import com.example.LibraryManagement.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        Author author = new Author();
        
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);
        return "Author Added";
    }
    public AuthorResponseDto getAuthor(Integer authorId){
        Author author = authorRepository.findById(authorId).get();
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        List<Book> bookList = author.getBooks();

        List<BookResponseDto> booksWrittenDto = new ArrayList<>();

        for(Book b : bookList){

            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());

            booksWrittenDto.add(bookResponseDto);
        }
        //Set attributes for authorResponse Dto
        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());

        return authorResponseDto;
    }

    public List<String> getBooks(@RequestParam Integer id)
    {
        return bookRepository.getBooks(id);
    }
}
