package com.example.LibraryManagement.Repositories;

import com.example.LibraryManagement.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    @Query(value = "select * from transactions where book_id=:bookId and card_id=:cardId and is_issued_operation=true", nativeQuery = true)
    List<Transactions> getTransactionsForBookAndCard(int bookId, int cardId);

    @Query(value = "select transaction_date from transactions where book_id=:bookId and card_id=:cardId and is_issued_operation=true limit 1 ", nativeQuery = true)
    LocalDate getLast(int bookId, int cardId);

    @Query(value = "select * from transactions where card_id=:cardId and transaction_status='PENDING' limit 1 ", nativeQuery = true)
    Transactions payFine(int cardId);


}
