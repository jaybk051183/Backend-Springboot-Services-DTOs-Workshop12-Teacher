package com.example.les12services.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class TeacherDto {
    public Long id;

    @NotBlank
    public String firstName;

    @Size(min = 3, max = 6)
    public String lastName;

    @Past
    public LocalDate dob;

    @NotNull(message = "Salary is required")
    @Max(value = 100000, message = "Salary cannot be more than 100,000")
    public int salary;
}
