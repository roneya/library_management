package com.example.LibraryManagement.Services;

import com.example.LibraryManagement.DTOs.IssueBookRequestDto;
import com.example.LibraryManagement.Enums.CardStatus;
import com.example.LibraryManagement.Enums.TransactionStatus;
import com.example.LibraryManagement.Models.Book;
import com.example.LibraryManagement.Models.Card;
import com.example.LibraryManagement.Models.Transactions;
import com.example.LibraryManagement.Repositories.BookRepository;
import com.example.LibraryManagement.Repositories.CardRepository;
import com.example.LibraryManagement.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception
    {
        int bookId = issueBookRequestDto.getBookId();
        int cardId  = issueBookRequestDto.getCardId();

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        Transactions transactions = new Transactions();
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssuedOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        if(book==null || book.isIssued()){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            throw new Exception("Book is not available");
        }

        if(card==null || card.getCardStatus()!= CardStatus.ACTIVATED) {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card is not valid");
        }


        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);

        List<Transactions> listOfTransactionForBook = book.getListOfTransactions();
        listOfTransactionForBook.add(transactions);
        book.setListOfTransactions(listOfTransactionForBook);

        List<Book> issuedBooksForCard = card.getBooks();
        issuedBooksForCard.add(book);
        card.setBooks(issuedBooksForCard);


        List<Transactions> transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transactions);
        card.setTransactionsList(transactionsListForCard);

        cardRepository.save(card);


        return "Book issued successfully";
    }
        public String getTransactions(int bookId, int cardId){
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);
        String transactionId =  transactionsList.get(0).getTransactionId();
        return transactionId;
        }

}
