package com.github.jusan.school.user.exceptions;

public class StudentNotFound extends RuntimeException {
    public StudentNotFound(Long id) {
        super(String.format("Could not find student %d", id));
    }
}
