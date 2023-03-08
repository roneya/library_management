package com.example.LibraryManagement.Repositories;

import com.example.LibraryManagement.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {



}
