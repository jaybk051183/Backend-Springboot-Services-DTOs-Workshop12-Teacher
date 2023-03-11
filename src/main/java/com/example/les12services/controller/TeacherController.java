//This class is a controller for a RESTful API that allows clients to retrieve and manipulate teacher data.
// It uses the Spring framework to handle HTTP requests and responses, and relies on an instance of the TeacherService class to perform operations on teacher data.

package com.example.les12services.controller;

import com.example.les12services.dto.TeacherDto;
import com.example.les12services.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Class declaration extending the @RestController annotation. This indicates that this class is a RESTful controller that will handle HTTP requests and responses.
@RestController

// The @RequestMapping("teachers") annotation specifies the base URL for all the endpoints defined in this class.
@RequestMapping("teachers")
public class TeacherController {

    private final TeacherService service;

    //The constructor for this class takes an instance of the TeacherService class as a parameter, which will be used to perform operations on teacher data.

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    //The first HTTP method defined in this class is the @GetMapping("/{id}") method. This method retrieves a teacher with the given ID by calling the getTeacher() method of the TeacherService instance. It then returns a ResponseEntity object containing the TeacherDto object returned by the service method.

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long id) {
        TeacherDto tdto = service.getTeacher(id);
        return ResponseEntity.ok(tdto);
    }

    //The next HTTP method is the @PostMapping method, which creates a new teacher with the data provided in the request body.
    // The TeacherDto object is validated using the @Valid annotation, and any validation errors are collected in a BindingResult object.
    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto tdto, BindingResult br) {

        // If there are any validation errors, the method returns a ResponseEntity object with a HTTP status of BAD_REQUEST and an error message containing the validation errors.
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField()).append(": ");
                sb.append(fe.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        // If there are no validation errors, the TeacherService instance's createTeacher() method is called to create the teacher.

        Long id = service.createTeacher(tdto);
        tdto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + id).toUriString());
//The URI of the newly created teacher is then returned in the HTTP response headers.
        return ResponseEntity.created(uri).body(tdto);
    }

    //The last HTTP method defined in this class is the @GetMapping method, which retrieves all the teachers by calling the getAllTeachers() method of the TeacherService instance. The list of TeacherDto objects is then returned in a ResponseEntity object.

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teacherDtos = service.getAllTeachers();
        return ResponseEntity.ok(teacherDtos);
    }

}
