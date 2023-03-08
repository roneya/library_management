package com.example.LibraryManagement.Repositories;

import com.example.LibraryManagement.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "select book.name, author.name from book join author where author.id=book.author_id", nativeQuery = true)
    List<String> allBooks();

    @Query(value = "select name from book where author_id=:id ",nativeQuery = true)
    List<String> getBooks( Integer id);

    @Query(value = "select name from book where genre=:sub", nativeQuery = true)
    List<String> subBooks(String sub);
}
