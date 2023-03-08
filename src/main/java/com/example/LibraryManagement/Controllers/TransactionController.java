package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.DTOs.IssueBookRequestDto;
import com.example.LibraryManagement.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("issuedBook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto)
    {
        try{
            return transactionService.issueBook(issueBookRequestDto);
        } catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("submitBook")
    public String submitBook(@RequestBody IssueBookRequestDto issueBookRequestDto){

            return transactionService.submitBook(issueBookRequestDto);

    }

    @GetMapping("getTransactionInfo")
    public String getTransactionEntry(@RequestParam Integer bookId, @RequestParam Integer cardId){
        return transactionService.getTransactions(bookId,cardId);
    }

}
