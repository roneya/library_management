package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.DTOs.IssueBookRequestDto;
import com.example.LibraryManagement.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto)
    {
         return "";
    }

}
