package com.yuiborodin.sweb.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;

    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public Student getStudent(Long id){
        return studentRepository.findById(id) //
                .orElseThrow(() -> new IllegalStateException("Email already exists"));
    }

    public Student addStudent(Student student) {
        Optional<Student> studentByEmail =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        else{
            studentRepository.save(student);
        }
        return student;
        // System.out.println(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw new IllegalStateException(
                    "Student with id " + id + " does not exist"
            );
        }
        else{
            studentRepository.deleteById(id);
        }

    }

    @Transactional
    public Student updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student does not exist"
                ));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            student.setEmail(name);
        }
        studentRepository.save(student);
        return student;
    }
}
