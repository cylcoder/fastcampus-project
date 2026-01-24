package com.example.academy.repo;

import com.example.academy.domain.Student;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentRepository {

  private final Map<String, Student> studentMap = new HashMap<>();

  public void save(Student student) {
    studentMap.put(student.getName(), student);
  }

  public Student findByName(String name) {
    return Optional.ofNullable(studentMap.get(name))
        .orElseThrow(IllegalArgumentException::new);
  }
}
