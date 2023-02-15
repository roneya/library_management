package com.example.LibraryManagement.Repositories;

import com.example.LibraryManagement.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByEmail(String email); //only object can be used

    List<Student> findByCountry(String country);
}
