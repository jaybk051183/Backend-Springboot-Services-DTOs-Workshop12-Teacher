//This code defines a Java class that provides a service layer for handling operations on Teacher entities.
// The purpose of this class is to provide a clear and consistent way of interacting with the repository layer and to ensure that different parts of the application can work together seamlessly.

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

//The @Service annotation on the class specifies that this class is a Spring service bean.
@Service
public class TeacherService {

    //The class includes instance variable for a TeacherRepository object, which is used to access the database.
    private final TeacherRepository repos;

    //The constructor for this class takes a single parameter, a TeacherRepository object, which is injected by Spring.
    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    //The class includes several methods for performing operations on Teacher entities, including:
    // creating a new teacher, retrieving a teacher by ID, and retrieving all teachers.

//The createTeacher() method takes a TeacherDto object as a parameter, creates a new Teacher entity based on this object, and saves it to the database using the TeacherRepository.
    public Long createTeacher(TeacherDto tdto) {

        //Before creating the new teacher entity, the method checks if a teacher with the same first name and last name already exists in the database. If so, it throws a ConflictException.
        Optional<Teacher> existingTeacher = repos.findByFirstNameAndLastName(tdto.firstName, tdto.lastName);
        if (existingTeacher.isPresent()) {
            //If the last name of the teacher being created is too long, a NameTooLongException is thrown.
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

    //The getTeacher() method retrieves a Teacher entity by ID from the database using the TeacherRepository and maps it to a TeacherDto object.
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

    //The getAllTeachers() method retrieves all Teacher entities from the database using the TeacherRepository and maps each entity to a TeacherDto object. The list of TeacherDto objects is then returned.
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
