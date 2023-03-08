package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Repositories.BookRepository;
import com.example.LibraryManagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("add")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public String addBook(@RequestBody BookRequestDto bookRequestDto)
    {
        return bookService.addBook(bookRequestDto);
    }
    @GetMapping("allBooks") //all book name and author names
    public List<String> allBooks(){
        return bookService.allBooks();
    }


    @GetMapping("subBooks")  //all books related with that subject
    public List<String> subBooks(@RequestParam String sub){
        return bookService.subBooks(sub.toUpperCase());
    }

}
