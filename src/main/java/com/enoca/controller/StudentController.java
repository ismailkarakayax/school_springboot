package com.enoca.controller;

import com.enoca.model.dto.StudentDto;
import com.enoca.model.entity.Student;
import com.enoca.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //localhost:8080/api/student/getall
    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getAllStudents(){

        try{
            List<Student> temp =  studentService.getAllStudents();
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    //localhost:8080/api/student/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        try{
            Student temp =  studentService.getStudentById(id);
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }


    //localhost:8080/api/student/getbyteacherid/{id}
    @GetMapping("/getbyteacherid/{id}")
    public ResponseEntity<List<Student>> getByTeacherId(@PathVariable Long id){
        try{
            List<Student> temp =  studentService.findByTeacherId(id);
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //localhost:8080/api/student/create
    @PostMapping("/create")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto studentDto){
        try{
            Student temp =  studentService.saveStudent(studentDto);
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //localhost:8080/api/student/put/{id}
    @PutMapping("/put/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        try{
            Student temp =  studentService.putStudent(id, studentDto);
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //localhost:8080/api/student/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        try{
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }



}
