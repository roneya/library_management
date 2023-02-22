package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.DTOs.AuthorEntryDto;
import com.example.LibraryManagement.DTOs.AuthorResponseDto;
import com.example.LibraryManagement.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @PostMapping("add")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public String addAuthor(@RequestBody AuthorEntryDto authorEntryDto)
    {
        return authorService.createAuthor(authorEntryDto);

    }

    @GetMapping("getAuthor")
    public AuthorResponseDto getAuthor(@RequestParam Integer authorId)
    {
        return authorService.getAuthor(authorId);
    }
}
