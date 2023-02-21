package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Models.Author;
import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Repositories.AuthorRepository;
import com.example.LibraryManagement.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto)
    {
        Book book = new Book();
        int authorId = bookRequestDto.getAuthorId();
        Author author = authorRepository.findById(authorId).get();
        book.setAuthor(author);
        book.setPages(bookRequestDto.getPages());
        book.setGenre(bookRequestDto.getGenre());
        book.setName(bookRequestDto.getName());
        book.setIssued(false);

        List<Book> books = author.getBooks();
        books.add(book);


        authorRepository.save(author);

        return "Book Added";
    }
}
