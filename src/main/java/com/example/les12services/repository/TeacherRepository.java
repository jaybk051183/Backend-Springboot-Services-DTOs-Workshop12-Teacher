package com.example.les12services.repository;

import com.example.les12services.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Iterable<Teacher> findByDobBefore(LocalDate date);
    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);

}
