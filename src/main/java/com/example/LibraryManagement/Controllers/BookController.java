package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.DTOs.BookRequestDto;
import com.example.LibraryManagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("add")
    public String addBook(@RequestBody BookRequestDto bookRequestDto)
    {
        return bookService.addBook(bookRequestDto);
    }
}
