package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("add")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public String addBook(@RequestBody BookRequestDto bookRequestDto)
    {
        return bookService.addBook(bookRequestDto);
    }
}
