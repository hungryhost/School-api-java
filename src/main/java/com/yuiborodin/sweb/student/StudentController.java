package com.yuiborodin.sweb.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/students")
public class StudentController {
    private final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping
    List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("{id}")
   Student one(@PathVariable Long id) {
        return studentService.getStudent(id);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public Student updateStudent(
            @PathVariable("studentId") Long id,
            @RequestBody Student student){
        return studentService.updateStudent(id, student.getName(), student.getEmail());
    }
}
