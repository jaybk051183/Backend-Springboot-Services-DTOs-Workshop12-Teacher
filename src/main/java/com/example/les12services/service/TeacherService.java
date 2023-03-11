package com.example.les12services.service;

import com.example.les12services.dto.TeacherDto;
import com.example.les12services.exception.ConflictException;
import com.example.les12services.exception.NameTooLongException;
import com.example.les12services.exception.ResourceNotFoundException;
import com.example.les12services.model.Teacher;
import com.example.les12services.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    public Long createTeacher(TeacherDto tdto) {
        Optional<Teacher> existingTeacher = repos.findByFirstNameAndLastName(tdto.firstName, tdto.lastName);
        if (existingTeacher.isPresent()) {
            throw new ConflictException("Teacher with the same name already exists");
        }

        // mapping
        Teacher t = new Teacher();
        t.setFirstName(tdto.firstName);
        t.setLastName(tdto.lastName);
        t.setDob(tdto.dob);
        t.setSalary(tdto.salary);

        try{
            repos.save(t);
        }
        catch (Exception e){
            throw new NameTooLongException("Lastname is too long");
        }
        return t.getId();
    }

    public TeacherDto getTeacher(Long id) {
        Teacher t = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        // mapping
        TeacherDto tdto = new TeacherDto();
        tdto.id = t.getId();
        tdto.firstName = t.getFirstName();
        tdto.lastName = t.getLastName();
        tdto.dob = t.getDob();

        return tdto;
    }

    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = (List<Teacher>) repos.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (Teacher t : teachers) {
            TeacherDto tdto = new TeacherDto();
            tdto.id = t.getId();
            tdto.firstName = t.getFirstName();
            tdto.lastName = t.getLastName();
            tdto.dob = t.getDob();
            teacherDtos.add(tdto);
        }
        return teacherDtos;
    }

}
