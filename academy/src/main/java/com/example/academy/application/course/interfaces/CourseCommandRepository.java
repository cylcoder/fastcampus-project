package com.example.academy.application.course.interfaces;

import com.example.academy.domain.Course;

public interface CourseCommandRepository {

  void save(Course course);

}
