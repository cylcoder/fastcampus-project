package org.fastcampus.student_management.repo;

import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.domain.Course;

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
