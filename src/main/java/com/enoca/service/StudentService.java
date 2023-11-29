package com.enoca.service;

import com.enoca.model.entity.Student;
import com.enoca.model.dto.StudentDto;

import com.enoca.model.entity.Teacher;
import com.enoca.repository.StudentRepository;
import com.enoca.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findByTeacherId(Long teacherId){


        Optional<List<Student>> studentOptional = Optional.ofNullable(studentRepository.findByTeacherId(teacherId));
        return studentOptional.orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + teacherId));
    }

    public Student getStudentById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        return studentOptional.orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
    }

    public Student saveStudent(StudentDto studentDto){

        // Öğrenci DTO'sundan öğrenci nesnesine dönüştürme
        Student student = new Student();
        student.setStudentName(studentDto.getStudentName());
        student.setGrade(studentDto.getGrade());

        // Öğretmenin varlığını kontrol etme
        Teacher teacher = teacherRepository.findById(studentDto.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + studentDto.getTeacherId()));

        student.setTeacher(teacher);

        // Öğrenciyi kaydetme
        return studentRepository.save(student);

    }

    public Student putStudent(Long studentId, StudentDto studentDto) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);

        Student existingStudent = existingStudentOptional.orElseThrow(() ->
                new EntityNotFoundException("Student not found with id: " + studentId));

        // Güncellenmiş bilgileri ayarla
        existingStudent.setStudentName(studentDto.getStudentName());
        existingStudent.setGrade(studentDto.getGrade());

        // Öğretmenin varlığını kontrol etme ve ayarla
        Teacher teacher = teacherRepository.findById(studentDto.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + studentDto.getTeacherId()));

        existingStudent.setTeacher(teacher);

        // Güncellenmiş öğrenciyi kaydetme
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student not found with id: " + studentId);
        }

        // Öğrenciyi silme
        studentRepository.deleteById(studentId);
    }

}
