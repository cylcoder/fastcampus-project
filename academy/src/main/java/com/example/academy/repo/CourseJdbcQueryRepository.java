package com.example.academy.repo;

import com.example.academy.application.course.interfaces.CourseQueryRepository;
import com.example.academy.domain.Course;
import com.example.academy.domain.DayOfWeek;
import java.util.List;

public class CourseJdbcQueryRepository implements CourseQueryRepository {

  @Override
  public List<Course> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    return List.of();
  }

  @Override
  public List<Course> getCourseListByStudent(String studentName) {
    return List.of();
  }

}
