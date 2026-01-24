package com.example.academy.repo;

import com.example.academy.application.course.interfaces.CourseCommandRepository;
import com.example.academy.domain.Course;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseCommandRepositoryImpl implements CourseCommandRepository {

  private final Map<String, Course> courseMap = new HashMap<>();

  public void save(Course course) {
    courseMap.put(course.getCourseName(), course);
  }

  public void saveCourses(List<Course> courses) {
    for (Course course : courses) {
      courseMap.put(course.getCourseName(), course);
    }
  }
}
