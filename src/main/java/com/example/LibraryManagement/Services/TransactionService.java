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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        transactions.setFine(0);
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
        book.setCard(card);

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
    public String submitBook(IssueBookRequestDto issueBookRequestDto)
    {
        int bookId = issueBookRequestDto.getBookId();
        int cardId  = issueBookRequestDto.getCardId();

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        Transactions transactions = new Transactions(); //new transaction
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssuedOperation(false);
        transactions.setTransactionStatus(TransactionStatus.PENDING);







        LocalDate localDate1 = transactionRepository.getLast(bookId,cardId); //old
        transactions.setTransactionDate(LocalDate.now());  //(LocalDate.of(2023, 4, 15))
        LocalDate localDate = transactions.getTransactionDate();
        int days = (int)ChronoUnit.DAYS.between(localDate1, localDate);
        days=days-7;
        if(days<0) transactions.setFine(0); //we can skip it as by defaults its 0
        else transactions.setFine(days*10); //fine 10 rupees per day after 7 days


        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(false); //now its not issued but returned
        book.setCard(null);


        //List<Transactions> listOfTransactionForBook = book.getListOfTransactions();
        //listOfTransactionForBook.add(transactions);
        //book.setListOfTransactions(listOfTransactionForBook);

        List<Book> issuedBooksForCard = card.getBooks();
        issuedBooksForCard.remove(book);
        card.setBooks(issuedBooksForCard);

        List<Transactions> transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transactions);
        card.setTransactionsList(transactionsListForCard);



        cardRepository.save(card);
        return "Book has been submitted successfully";
    }








        public String getTransactions(int bookId, int cardId){
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);
        String transactionId =  transactionsList.get(0).getTransactionId();
        return transactionId;
        }

}
