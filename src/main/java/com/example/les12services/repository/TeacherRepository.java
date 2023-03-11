//This code defines a Java interface that specifies a contract for a repository that handles operations on Teacher entities. The purpose of this interface is to provide a clear and consistent reference point for developers who are working on different parts of the application, and to ensure that different parts of the application can work together seamlessly.

package com.example.les12services.repository;

import com.example.les12services.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

//The interface extends the Spring Data CrudRepository interface, which provides basic CRUD (Create, Read, Update, Delete) operations for entities.
//The generic type parameters <Teacher, Long> specify that this repository is for the Teacher entity and that the primary key for this entity is of type Long.
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    //The interface includes two additional methods, findByDobBefore() and findByFirstNameAndLastName(), which are custom query methods specific to the Teacher entity.

    //The findByDobBefore() method takes a single parameter, a LocalDate date, and returns an Iterable of Teacher objects whose date of birth is before the specified date.
    Iterable<Teacher> findByDobBefore(LocalDate date);

    //The findByFirstNameAndLastName() method takes two parameters, the first name and last name of a teacher, and returns an Optional of a Teacher object whose first name and last name match the specified values.
    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);

}
