package com.enoca.controller;


import com.enoca.model.dto.TeacherDto;
import com.enoca.model.entity.Student;
import com.enoca.model.entity.Teacher;
import com.enoca.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //localhost:8080/api/teacher/getall
    @GetMapping("/getall")
    public ResponseEntity<List<Teacher>> getallTeachers(){
        try{
            List<Teacher> temp =  teacherService.getAllTeachers();
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //localhost:8080/api/teacher/get/
    @GetMapping("get/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id){
        try{
            Teacher temp =  teacherService.getTeacherById(id);
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //localhost:8080/api/teacher/create
    @PostMapping("/create")
    public ResponseEntity<Teacher> saveTeacher( @RequestBody TeacherDto teacherDto){

        try{
            Teacher temp = teacherService.saveTeacher(teacherDto);
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //localhost:8080/api/teacher/put/{id}
    @PutMapping("/put/{id}")
    public ResponseEntity<Teacher> putTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto){
        try{
            Teacher temp = teacherService.putTeacher(id, teacherDto);
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //localhost:8080/api/teacher/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id){
        try{
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
