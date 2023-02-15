package com.example.LibraryManagement.Services;


import com.example.LibraryManagement.Enums.CardStatus;
import com.example.LibraryManagement.Models.Card;
import com.example.LibraryManagement.Models.Student;
import com.example.LibraryManagement.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
   StudentRepository studentRepository;



    public String createStudent(Student student)
    {
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);
        return "Added";
    }
    public String findNameByEmail (String email){
    Student student =  studentRepository.findByEmail(email);
     return student.getName();
    }

    public String updateMob(Student student){

        Student student1 = studentRepository.findById(student.getId()).get();
        student1.setMobNo(student.getMobNo());
        studentRepository.save(student1);
        return "Student has been updated successfully";
    }

}
