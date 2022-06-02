package com.github.jusan.school.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service // 1
public class CustomUserDetailsService implements UserDetailsService {
    private StudentRepository studentRepository; // 2

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // 3
        // realization
        Student student = this.studentRepository.findStudentByEmail(email);
        if (student == null) {
            throw new UsernameNotFoundException("email " + email + " is not found");
        }
        return new CustomUserDetails(student);
    }
}