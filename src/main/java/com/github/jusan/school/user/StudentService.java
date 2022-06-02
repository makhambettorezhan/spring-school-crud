package com.github.jusan.school.user;

import com.github.jusan.school.user.exceptions.StudentNotFound;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private ModelMapper modelMapper;
    private StudentRepository studentRepository;

    public Long createStudent(StudentRequest userRequest) {
        Student user = Student.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .password(userRequest.getPassword())
                .lastName(userRequest.getLastName())
                .build();
        Student createdUser = studentRepository.save(user);
        return createdUser.getId();
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(() -> new StudentNotFound(id));
        studentRepository.deleteById(id);
    }

    public Student updateStudent(StudentRequest userRequest, Long id) {
        studentRepository.findById(id).orElseThrow(() -> new StudentNotFound(id));
        Student user = Student.builder().email(userRequest.getEmail()).firstName(userRequest.getFirstName())
                .password(userRequest.getPassword()).lastName(userRequest.getLastName()).id(id).build();
        return studentRepository.save(user);
    }

    public List<StudentResponse> getStudents() {

        List<StudentResponse> studentResponses = Streamable.of(studentRepository.findAll())
                .stream()
                .map(user -> modelMapper.map(user, StudentResponse.class))
                .collect(Collectors.toList());
        return studentResponses;
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFound(id));
        return modelMapper.map(student, StudentResponse.class);
    }
}
