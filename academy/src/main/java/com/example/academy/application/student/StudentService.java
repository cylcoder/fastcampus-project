package com.example.academy.application.student;

import com.example.academy.application.student.dto.StudentInfoDto;
import com.example.academy.domain.Student;
import com.example.academy.repo.StudentRepository;

public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void saveStudent(StudentInfoDto studentInfoDto) {
    Student student = new Student(studentInfoDto.getName(), studentInfoDto.getAge(), studentInfoDto.getAddress());
    studentRepository.save(student);
  }

  public Student getStudent(String name) {
    return studentRepository.findByName(name);
  }

  public void activateStudent(String name) {
    getStudent(name).activate();
  }

  public void deactivateStudent(String name) {
    getStudent(name).deactivate();
  }
}
