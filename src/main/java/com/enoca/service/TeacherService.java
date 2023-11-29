package com.enoca.service;

import com.enoca.model.entity.Teacher;
import com.enoca.model.dto.TeacherDto;
import com.enoca.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        return teacherOptional.orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));
    }

    public Teacher saveTeacher(TeacherDto teacherDTO) {
        // Öğretmen DTO'sundan öğretmen nesnesine dönüştürme
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherDTO.getTeacherName());

        // Öğretmeni kaydetme
        return teacherRepository.save(teacher);
    }
    public Teacher putTeacher(Long teacherId, TeacherDto teacherDTO) {
        Optional<Teacher> existingTeacherOptional = teacherRepository.findById(teacherId);

        Teacher existingTeacher = existingTeacherOptional.orElseThrow(() ->
                new EntityNotFoundException("Teacher not found with id: " + teacherId));

        // Güncellenmiş bilgileri ayarla
        existingTeacher.setTeacherName(teacherDTO.getTeacherName());

        // Güncellenmiş öğretmeni kaydetme
        return teacherRepository.save(existingTeacher);
    }
    public void deleteTeacher(Long teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new EntityNotFoundException("Teacher not found with id: " + teacherId);
        }

        // Öğretmeni silme
        teacherRepository.deleteById(teacherId);
    }

}
