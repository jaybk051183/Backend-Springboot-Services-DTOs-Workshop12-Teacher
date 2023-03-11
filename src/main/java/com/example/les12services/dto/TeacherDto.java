//This is a Java class with instance variables and annotations that provide validation rules for the TeacherDto object. The purpose of this object is to transfer data between layers of the application, and the annotations help ensure that the data conforms to specific constraints.
//This class is a plain Java object (POJO) used to transfer data between the controller and service layers of the application. It does not contain any business logic or methods, and is used solely for data transfer purposes.

package com.example.les12services.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class TeacherDto {
    //The class includes instance variables for the ID, first name, last name, date of birth, and salary of a teacher.
    public Long id;

    //The @NotBlank annotation on the firstName field specifies that this field must not be blank or null
    @NotBlank
    public String firstName;

    //The @Size annotation on the lastName field specifies that the length of the string must be between 3 and 6 characters.
    @Size(min = 3, max = 6)
    public String lastName;

    //The @Past annotation on the dob field specifies that the date must be in the past.
    @Past
    public LocalDate dob;

    //The @NotNull annotation on the salary field specifies that this field cannot be null.
    //The @Max annotation on the salary field specifies that the maximum value for this field is 100,000.
    @NotNull(message = "Salary is required")
    @Max(value = 100000, message = "Salary cannot be more than 100,000")
    public int salary;

    //The purpose of these annotations is to provide validation rules for the TeacherDto object. These rules ensure that the data being transferred conforms to specific constraints.
}
