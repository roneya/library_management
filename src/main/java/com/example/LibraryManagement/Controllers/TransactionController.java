package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.DTOs.IssueBookRequestDto;
import com.example.LibraryManagement.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("issuedBook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto)
    {
        return transactionService.issueBook(issueBookRequestDto);
    }

}
