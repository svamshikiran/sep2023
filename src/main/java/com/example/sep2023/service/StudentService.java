package com.example.sep2023.service;

import com.example.sep2023.model.Student;
import com.example.sep2023.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository stuRepo;


    public List<Student> getAllStudents(){
        //System.out.println("INSIDE SERVICE");
        return stuRepo.findAll();//select * from student;
    }

    public Student getStudentByRollno(int rollno){
        System.out.println("INSIDE THE SERVICE - input: "+rollno);
        Optional<Student> opStudent = stuRepo.findById(rollno);
        //select * from student where rollno=?
        if(opStudent.isPresent())
            return opStudent.get();
        else
            return new Student();
    }

    public List<Student> getStudentByCity(String city){
        return stuRepo.findByCity(city);
        //select * from student where city=?
    }

    public void upsert(Student student) {
        stuRepo.save(student);
        //if the record is present - it will update the values
        //else it will insert the record
        //insert into student values (student.getRollno(), student.getName().....
    }

    public boolean isStudentPresent(int rollno){
        Optional<Student> opStudent = stuRepo.findById(rollno);
        if(opStudent.isPresent())
            return true;
        else
            return false;
    }

    public void deleteStudent(int rollno){
        stuRepo.deleteById(rollno);
    }
}
