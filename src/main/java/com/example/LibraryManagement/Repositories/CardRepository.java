package com.example.LibraryManagement.Repositories;

import com.example.LibraryManagement.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer > {

}
