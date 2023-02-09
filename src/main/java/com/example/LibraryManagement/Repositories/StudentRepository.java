package com.example.LibraryManagement.Repositories;

import com.example.LibraryManagement.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
