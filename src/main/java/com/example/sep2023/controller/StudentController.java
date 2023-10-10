package com.example.sep2023.controller;

import com.example.sep2023.model.Student;
import com.example.sep2023.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/get/{rollno}")
    public ResponseEntity<Object> getStudentByRollno(@PathVariable("rollno") int rollno){
        System.out.println("INSIDE THE CONTROLLER - input: "+rollno);
        Student student = studentService.getStudentByRollno(rollno);
        if(student.getRollno() == 0){
            return new ResponseEntity<>("STUDENT DOESN'T EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getbycity/{city}")
    public List<Student> getStudentsByCity(@PathVariable("city") String city){
        return studentService.getStudentByCity(city);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody Student student){
        if(student.getRollno() <= 0)
            return new ResponseEntity<>("ROLLNO IS INVALID/MISSING - PLEASE CHECK AND ADD AGAIN", HttpStatus.BAD_REQUEST);
        try {
            studentService.upsert(student);
            return new ResponseEntity<>("STUDENT ADDED SUCCESSFULLY", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("EXCEPTION OCCURED WHILE EXECUTING DB OPERATION, PLEASE CHECK WITH ADMINISTRATOR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student){
        if(!studentService.isStudentPresent(student.getRollno()))
            return new ResponseEntity<>("ROLLNO IS INVALID/MISSING - PLEASE CHECK AND ADD AGAIN", HttpStatus.BAD_REQUEST);
        try {
            studentService.upsert(student);
            return new ResponseEntity<>("STUDENT UPDATED SUCCESSFULLY", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("EXCEPTION OCCURED WHILE EXECUTING DB OPERATION, PLEASE CHECK WITH ADMINISTRATOR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{rollno}")
    public void deleteStudent(@PathVariable("rollno") int rollno){
        studentService.deleteStudent(rollno);
    }



    /*
        @Operation(description = "This method retrieves the student details based on the rollno" +
            "provided. If exists, it returns the 200(OK) response, else it returns" +
            "400(BAD_REQUEST) response")
     */

}
