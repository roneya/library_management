
package com.example.LibraryManagement.Controllers;


import com.example.LibraryManagement.DTOs.StudentUpdateMobRequestDto;
import com.example.LibraryManagement.Models.Student;
import com.example.LibraryManagement.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://127.0.0.1:5500") //frontend address on local machine
    public String createStudent(@RequestBody Student student)
    {
        return studentService.createStudent(student);

    }

    @GetMapping("/get_user")
    public String getNameByEmail(@RequestParam("email") String email ) {
        return studentService.findNameByEmail(email);
    }

       @PutMapping("/update_mob")
               public String updateMob(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto){

        return  studentService.updateMob(studentUpdateMobRequestDto);
        }
    }

