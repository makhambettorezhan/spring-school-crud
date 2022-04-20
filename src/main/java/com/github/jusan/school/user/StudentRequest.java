package com.github.jusan.school.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {
        private String firstName;
        private String lastName;
        private String password;
        private String email;
}
